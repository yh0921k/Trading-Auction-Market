package kyh.tam.handler;

import kyh.util.Prompt;

public class HelloCommand implements Command {
  Prompt prompt;

  public HelloCommand(Prompt prompt) {
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.printf("%s님 안녕하세요.\n", prompt.inputString("이름 : "));
  }
}
