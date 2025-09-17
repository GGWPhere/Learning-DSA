//Goal: Simulate browser's back and forward buttons

//Why use Stacks
/*backStack: pages visited before the current one
 *forwardStack: pages you can go forward to
 */

 //Operation
 /* Visit(page)- push current page to backStack, clear forwardStack
  * back()- push current page to forwardStack, pop from backStack
  * forward()- push current page to backStack, pop from forwardStack
  */

  //import java.util.*;
/* 
  public class BrowserHistory{
    private Deque<String> back = new ArrayDeque<>();
    private Deque<String> forward = new ArrayDeque<>();
    private String current = "Home";

    public void visit(String page){
        back.push(current);
        current = page;
        forward.clear();
    }
    public void back(){
        if(!back.isEmpty()){
            forward.push(current);
            current = back.pop();
        }
    }
    public void forward(){
        if(!forward.isEmpty()){
            back.push(current);
            current = forward.pop();
        }
    }
    public String getCurrent(){return current;}
    public static void main(String[] args){
        BrowserHistory bh = new BrowserHistory();
        bh.visit("google.com");
        bh.visit("github.com");
        bh.back();
        System.out.println(bh.getCurrent());
        bh.forward();
        System.out.println(bh.getCurrent());
    }
  }*/