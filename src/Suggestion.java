import Exceptions.AdminAccessRequiredException;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class Suggestion {
    ReportStatus suggestionStatus;
    Member editor;
    Member suggestionHandler;
    String suggestionDescription;
    Consumer<Object> methodToCall;// this is a method for a particular instance of an object so there is no need to get that object in any other parameter
    Object newValue;

    public Member getEditor() {
        return editor;
    }

    public Member getSuggestionHandler() {
        return suggestionHandler;
    }

    public String getSuggestionDescription() {
        return suggestionDescription;
    }

    public Consumer<Object> getMethodToCall() {
        return methodToCall;
    }

    public Object getNewValue() {
        return newValue;
    }


    public Suggestion(Member editor, String suggestionDescription, Consumer<Object> methodToCall, Object newValue) {
        this.suggestionStatus = ReportStatus.UNHANDLED;
        this.editor = editor;
        this.suggestionHandler = null;
        this.suggestionDescription = suggestionDescription;
        this.methodToCall = methodToCall;
        this.newValue = newValue;
    }
    public void approveSuggestion(Member suggestionHandler) throws AdminAccessRequiredException {
        if(suggestionHandler.getAccessLevel().equals(AccessLevel.ADMIN)) {
            this.suggestionHandler = suggestionHandler;
            this.suggestionStatus = ReportStatus.HANDLED;
            methodToCall.accept(newValue);
            DBLists.removeFromUnhandledSuggestions(this);
            DBLists.addToHandledSuggestions(this);
        }
        else {
            throw new AdminAccessRequiredException();
        }
    }
    public void disapproveSuggestion(Member suggestionHandler) throws AdminAccessRequiredException {
        if(suggestionHandler.getAccessLevel().equals(AccessLevel.ADMIN)) {
            this.suggestionHandler = suggestionHandler;
            this.suggestionStatus = ReportStatus.HANDLED;
            DBLists.removeFromUnhandledSuggestions(this);
            DBLists.addToHandledSuggestions(this);
        }
        else {
            throw new AdminAccessRequiredException();
        }
    }
}
