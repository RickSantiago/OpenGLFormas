package computacaografica.openglincial.formas;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by silvano on 8/15/16.
 */
public class Quadrado extends Forma
{

    public void desenha(GL10 vrOpenGL)
    {
        vrOpenGL.glVertexPointer(2, GL10.GL_FLOAT, 0, vetorVertices);
        vrOpenGL.glColor4f(corR, corG, corB, 1.0f);
        vrOpenGL.glLoadIdentity();
        vrOpenGL.glTranslatef(posX, posY, 0);
        vrOpenGL.glRotatef(angulo, 0, 0, 1);
        vrOpenGL.glScalef(escala, escala, 1);
        vrOpenGL.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }

    public void defineForma()
    {
        float[] vertices = {-2.0f, -2.0f,
                -2.0f, 2.0f,
                2.0f, -2.0f,
                2.0f, 2.0f};

        this.vetorVertices = retornaVetorC(vertices);
    }
}
