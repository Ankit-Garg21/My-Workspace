package com.first.ankit;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEx {
	
	private float vertices[] = {
			0f, 1f,	//p0 Vertex 0
			1f, -1f,	// p1 Vertex 1
			-1f, -1f	// p2 Vertex 2
	};
	private FloatBuffer vertBuff;
	
	private short[] pIndex = {0, 1, 2};
	private ShortBuffer pointBuff;
	
	public GLTriangleEx() {
		/* ByteBuffer is used to tell how much space is being used and for allocation
		 * 1 float takes 4 bytes.
		 * Handles vertices
		 */
		ByteBuffer bBuff = ByteBuffer.allocateDirect((vertices.length)*4);
		// Set Up the order within the byte buffer
		bBuff.order(ByteOrder.nativeOrder());
		// Relate to the vertBuff (FloatBuffer) 
		vertBuff = bBuff.asFloatBuffer();
		//Put the list in the vertBuff (FloatBuffer)
		vertBuff.put(vertices);
		// Which position to start
		vertBuff.position(0);
		
		// Handles index
		ByteBuffer pBuff = ByteBuffer.allocateDirect((pIndex.length)*2);
		pBuff.order(ByteOrder.nativeOrder());
		pointBuff = pBuff.asShortBuffer();
		pointBuff.put(pIndex);
		pointBuff.position(0);
	}
	// Method to draw triangle
	public void Draw(GL10 gl){
		/* Connect all of the points
		 * GL_CW : Clockwise
		 */
		gl.glFrontFace(GL10.GL_CW);
		// Relate to client state and enable it for vertices array
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		/* glVertexPointer : Size (no. of axes)
		 * type (float)
		 * stride (skip over s dimension)
		 * pointer (vertex buffer)
		 */
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
		
		/* Draw triangle method
		 * mode : how the triangle is to be created
		 * count : no. of points
		 * type : type of points
		 * buffer.
		 */
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pointBuff);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}