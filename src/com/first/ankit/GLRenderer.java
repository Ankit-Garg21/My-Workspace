package com.first.ankit;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GLRenderer implements Renderer {

	private GLTriangleEx tri;
	public GLRenderer() {
		tri = new GLTriangleEx();
	}
	//Draw like canvas
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		/* Color buffer.. which color to set
		 * Handles the color, depth etc..
		 * GL_COLOR_BUFFER_BIT : Buffer to handle color
		 * GL_DEPTH_BUFFER_BIT : Buffer to handle depth
		 */
		// Disable dither, improve performance over quality
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0, 0, 1, 0);
		
		tri.Draw(gl);
	}

	// On change from landscape to portrait
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		/* Set up view port, coordinates of view area
		 * According to given coordinates the whole screen is going to be a viewport
		 */
		gl.glViewport(0, 0, width, height);
		float ratio = (float) width/height;
		// Mode of the triangle, the way we want to view our triangle
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		/* How far in z direction we want our object to be seen
		 * left = -ratio
		 * right = ratio
		 * bottom = -1
		 * top = 1
		 * zNear (reference point to the view) = 1
		 * zFar (How far the view can go) = 25
		 */
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
	}

	// On create
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// Improve performance over quality
		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		
		gl.glClearColor(.8f, 0f, .2f, 1);
		gl.glClearDepthf(1f);
	}
}