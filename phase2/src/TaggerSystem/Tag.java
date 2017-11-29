package TaggerSystem;

import java.io.Serializable;

public class Tag implements Serializable {

  private String name;

  public Tag(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Tag) {
      return ((Tag) other).getName().equals(this.name);
    } else {
      return false;
    }
  }
}
