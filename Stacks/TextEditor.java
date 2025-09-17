//Goal: Support undo and Redo operations for typed text
//Idea
/*undoStack: holds actions performed
  redoStack: holds actions undone */

  //Operations
/* type(text)- push action to undoStack, clear redoStack
 * undo()- pop undoStack, push onto redoStack, revert text.
 * redo()- pop redoStack, push back to undoStack, reapply
 */

import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditor{
    private StringBuilder text = new StringBuilder();
    //stacks of actions
    private Deque<Action> undoStack = new ArrayDeque<>();
    private Deque<Action> redoStack = new ArrayDeque<>();

    //Actions
    private static class Action{
        enum Type { TYPE, DELETE }
        Type type;
        String content; //text typed of deleted 
        Action(Type type, String content){
            this.type = type;
            this.content = content;
        }
    }
    
    //public API
    //Type (append) some text
    public void type(String s){
        text.append(s);
        undoStack.push(new Action(Action.Type.TYPE, s));
        redoStack.clear(); //once you type, redo history lost
    }
    /** Delete lastn character */
    public void delete(int n){
        if(n<= 0 || n >text.length()) return;
        String removed = text.substring(text.length() - n);
        text.delete(text.length()-n, text.length());
        undoStack.push(new Action(Action.Type.DELETE, removed));
        redoStack.clear();
    }
    //Undo last action
    public void undo(){
        if(undoStack.isEmpty()) return;
        Action a = undoStack.pop();
        switch(a.type){
            case TYPE:
                text.delete(text.length() - a.content.length(), text.length());
                redoStack.push(new Action(Action.Type.TYPE, a.content));
                break;
            case DELETE:
                text.append(a.content);
                redoStack.push(new Action(Action.Type.DELETE, a.content));
                break;
        }
    }
    //redo last undone action
    public void redo(){
        if(redoStack.isEmpty()) return;
        Action a =redoStack.pop();
        switch (a.type){
            case TYPE:
                text.append(a.content);
                undoStack.push(new Action(Action.Type.TYPE, a.content));
                break;
            case DELETE:
                text.delete(text.length()-a.content.length(), text.length());
                undoStack.push(new Action(Action.Type.DELETE, a.content));
                break;
        }
    }
    //Redo the last undone action
    public void redo(){
        if(redoStack.isEmpty())return;
        Action a = redoStack.pop();
        switch (a) {
            case TYPE:
                text.append(a.content);
                undoStack.push(new Action(Action.Type.TYPE, a.content));
                break;
        
            case DELETE:
                text.delete(text.length() - a.content.length(), text.length());
                undoStack.push(new Action(Action.TYPE.DELETE, a.content));
                break;
        }
    }
    /** Get the current text. */
    public String getText() {
        return text.toString();
    }

    // ----------------- Demo --------------------
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.type("Hello");
        editor.type(" World");
        System.out.println(editor.getText()); // Hello World

        editor.delete(6);
        System.out.println(editor.getText()); // Hello

        editor.undo();
        System.out.println(editor.getText()); // Hello World

        editor.redo();
        System.out.println(editor.getText()); // Hello
    }
}