package redpill.engine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import redpill.engine.Camera;
import redpill.engine.GameItem;

import java.util.Vector;

public class Transformation {
    private final Matrix4f projectionMatrix;
    private final Matrix4f worldMatrix;
    private final Matrix4f viewMatrix;
    private final Matrix4f modelViewMatrix;

    public Transformation() {
        worldMatrix = new Matrix4f();
        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
        modelViewMatrix = new Matrix4f();
    }

    public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
        return projectionMatrix.setPerspective(fov,width/height,zNear,zFar);
    }

//    public Matrix4f getWorldMatrix(Vector3f offset, Vector3f rotation, float scale) {
//        return worldMatrix.translation(offset).
//                rotateX((float)Math.toRadians(rotation.x)).
//                rotateY((float)Math.toRadians(rotation.y)).
//                rotateZ((float)Math.toRadians(rotation.z)).
//                scale(scale);
//    }

    public Matrix4f getModelViewMatrix(GameItem gameItem, Matrix4f viewMatrix) {
        Vector3f rotation = gameItem.getRotation();
        modelViewMatrix.identity().translate(gameItem.getPosition()).
                rotateX((float)Math.toRadians(-rotation.x)).
                rotateY((float)Math.toRadians(-rotation.y)).
                rotateZ((float)Math.toRadians(-rotation.z)).
                scale(gameItem.getScale());
        Matrix4f viewCurr = new Matrix4f(viewMatrix);
        return viewCurr.mul(modelViewMatrix);
    }

    public Matrix4f getViewMatrix(Camera camera) {
        Vector3f cameraPos = camera.getPosition();
        Vector3f rotation = camera.getRotation();

        viewMatrix.identity();
        // First do rotation so camera rotates over its position
        viewMatrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1,0,0))
                .rotate((float)Math.toRadians(rotation.y), new Vector3f(0,1,0));
        // then do translation
        viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        return viewMatrix;
    }
}
