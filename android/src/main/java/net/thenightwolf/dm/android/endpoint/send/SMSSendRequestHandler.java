package net.thenightwolf.dm.android.endpoint.send;

import com.google.gson.Gson;
import net.thenightwolf.dm.android.DMApplication;
import net.thenightwolf.dm.android.endpoint.request.Authenticator;
import net.thenightwolf.dm.android.generator.message.send.IMessageSender;
import net.thenightwolf.dm.android.message.SmsManager;
import net.thenightwolf.dm.android.endpoint.request.StandardRequestHandler;
import net.thenightwolf.dm.android.utils.SessionUtils;
import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.response.IStatus;
import org.nanohttpd.protocols.http.response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static net.thenightwolf.dm.android.utils.SessionUtils.*;

public class SMSSendRequestHandler extends StandardRequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(SMSSendRequestHandler.class);
    private IMessageSender messageSender;
    private IStatus status;

    @Override
    public String putMethod(Map<String, String> params, IHTTPSession session) throws IOException, NanoHTTPD.ResponseException {
        return null;
    }

    @Override
    public String getMethod(Map<String, String> params, IHTTPSession session) throws IOException, NanoHTTPD.ResponseException {
        return null;
    }

    @Override
    public String postMethod(Map<String, String> params, IHTTPSession session) throws IOException, NanoHTTPD.ResponseException {

        logger.info("Checking authentication...");
        Authenticator auth = new Authenticator();
        if(auth.authenticate(session)) {
            String number = getSingleParam("number", session);
            String message = getSingleParam("message", session);
            messageSender.sendSMS(number, message);
            status = Status.OK;
            return new Gson().toJson("sent");
        } else {
            status = Status.UNAUTHORIZED;
            return auth.buildErrorResponse();
        }


    }

    @Override
    public String updateMethod(Map<String, String> params, IHTTPSession session) throws IOException, NanoHTTPD.ResponseException {
        return null;
    }

    @Override
    public String getMimeType() {
        return "application/json";
    }

    @Override
    public String getText() {
        return SessionUtils.buildError("400", "must supply parameters!");
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
