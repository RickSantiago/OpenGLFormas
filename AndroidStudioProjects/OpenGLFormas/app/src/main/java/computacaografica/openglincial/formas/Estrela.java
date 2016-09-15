package computacaografica.openglincial.formas;

/**
 * Created by RickSantiago on 15/08/2016.
 */
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by silvano on 8/22/16.
 */
public class Estrela extends Forma
{
    public void desenha(GL10 vrOpenGL)
    {
        vrOpenGL.glVertexPointer(2, GL10.GL_FLOAT, 0, vetorVertices);
        vrOpenGL.glColor4f(corR, corG, corB, 1.0f);
        vrOpenGL.glLoadIdentity();
        vrOpenGL.glTranslatef(posX, posY, 0);
        vrOpenGL.glRotatef(angulo, 0,0,1);
        vrOpenGL.glScalef(escala, escala, 1);
        vrOpenGL.glDrawArrays(GL10.GL_TRIANGLES, 0, 9);
    }

    public void defineForma()
    {
        float[] vertices = {
                0.0f,0.0f,
                -2.0f, -2.0f,
                2.0f, 0.0f,

                -2.0f, 0.0f,
                2.0f, -2.0f,
                0.0f,0.0f,

                0.0f, 1.5f,
                -0.42f, 0.0f,
                0.42f, 0.0f,


        };

        this.vetorVertices = retornaVetorC(vertices);
    }
}
