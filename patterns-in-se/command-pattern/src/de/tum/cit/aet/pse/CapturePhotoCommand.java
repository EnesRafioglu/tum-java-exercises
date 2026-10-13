package de.tum.cit.aet.pse;

public class CapturePhotoCommand implements Command {

    private Camera camera;

    public CapturePhotoCommand(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void execute() {
        camera.takePicture();
    }
}
