package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;

public class PlayerController {
    private float accelerationZ;
    private float accelerationX;

    public PlayerController(){
        accelerationZ = Gdx.input.getAccelerometerZ();
        accelerationX = Gdx.input.getAccelerometerX();
    }

    public void updateSensor(){
        accelerationZ = Gdx.input.getAccelerometerZ();
        accelerationX = Gdx.input.getAccelerometerX();
    }

    public float getAccelerationZ(){
        return this.accelerationZ;
    }

    public float getAccelerationX(){
        return  this.accelerationX;
    }
}
