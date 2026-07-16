package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import Model.*;
import Service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService acctService;
    MessageService msgService;

    public SocialMediaController() {
        this.acctService = new AccountService();
        this.msgService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getMessagesHandler);
        app.get("/messages/{message_id}", this::getMessagesByID);
        //TODO: finish delete
        app.delete("/messages/{message_id}", this::deleteMessagesByID);
        app.patch("/messages/{message_id}", this:updateMessagesByID);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void postRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account inputAcct = om.readValue(ctx.body(), Account.class);
        Account addedAcct = acctService.addAccount(inputAcct);
        if (addedAcct != null) {
            ctx.json(om.writeValueAsString(addedAcct));
        } else {
            ctx.status(400);
        }
    }
    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account inputAcct = om.readValue(ctx.body(), Account.class);
        Account matchingAcct = acctService.getAccountByLogin(inputAcct);
        if (matchingAcct != null) {
            ctx.json(om.writeValueAsString(matchingAcct));
        } else {
            ctx.status(401);
        }
    }
    private void postMessagesHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message inputMsg = om.readValue(ctx.body(), Message.class);
        Message addedMsg = msgService.addMessage(inputMsg);
        if (addedMsg != null) {
            ctx.json(om.writeValueAsString(addedMsg));
        } else {
            ctx.status(400);
        }
    }
    private void getMessagesHandler(Context ctx) throws JsonProcessingException {
        ctx.json(msgService.getAllMessages());
    }
    private void getMessagesByID(Context ctx) throws JsonProcessingException {
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        //TODO: I think there should be a more elegant solution for empty but I don't know what it is
        Message returnedMsg = msgService.getMessageByID(id);
        if (returnedMsg != null) {
            ctx.json(returnedMsg);
        }

    }
    private void deleteMessagesByID(Context ctx) throws JsonProcessingException {

    }
    private void updateMessagesByID(Context ctx) throws JsonProcessingException {
        int id = Integer.parseInt(ctx.pathParam("message_id"));

    }
}