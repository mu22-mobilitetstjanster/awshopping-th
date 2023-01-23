package nu.rolandsson.exception;

public class InvalidPasswordException extends RuntimeException {
  private String username;

  public InvalidPasswordException(String message, String username) {
    super(message);

    this.username = username;
  }


  public String getUsername() {
    return username;
  }
}
