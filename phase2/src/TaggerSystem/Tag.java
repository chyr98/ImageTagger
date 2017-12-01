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

  /**
   * Returns true if Other Object is a Tag and has same name as this Tag. Otherwise returns false.
   *
   * @param other An object we want to check
   * @return boolean
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Tag) {
      return ((Tag) other).getName().equals(this.name);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return this.name;
  }
}
