package com.first.ankit;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCubeEx {

	private float vertices[] = {
			1, 1, -1,	// Top Front Right
			1, -1, -1,	// Bottom Front Right
			-1, -1, -1,	// Bottom Front Left
			-1, 1, -1,	// Top Front Left
			1, 1, 1,	// Top Back Right
			1, -1, 1	// Bottom Back Right
			-1, -1, 1	// Bottom Back Left
			-1, 1, 1	// Top Back Left
	};
	private FloatBuffer vertBuff;
	
	private short pIndex[] = {
			3, 4, 0,	0, 4, 1,	3, 0, 1,
			3, 7, 4,	7, 6, 4,	7, 3, 6,
			3, 1, 2,	1, 6, 2,	6, 3, 2,
			1, 4, 5,	5, 6, 1,	6, 5, 4
	};
	private ShortBuffer shortBuff;
	
	public GLCubeEx() {
		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length*4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);
		
		ByteBuffer iBuff = ByteBuffer.allocateDirect(pIndex.length*2);
		iBuff.order(ByteOrder.nativeOrder());
		shortBuff = iBuff.asShortBuffer();
		shortBuff.put(pIndex);
		shortBuff.position(0);
	}
	
	public void Draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		
		// Enable culling to remove back face from the view
		gl.glEnable(GL10.GL_CULL_FACE);
		// Remove back face
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, shortBuff);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// Disable culling
		gl.glDisable(GL10.GL_CULL_FACE);
	}
}