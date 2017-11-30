package GUI.Controllers;

public interface RefreshableController {
    /**
     * Refresh the scene to catch the changes in back end data and display the updated information to
     * user.
     */
    void refresh();
}
