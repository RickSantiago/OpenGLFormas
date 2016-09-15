package computacaografica.openglincial.formas;

/**
 * Created by RickSantiago on 29/08/2016.
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class Forma
{
    int posX = 0, posY = 0;
    int corR = 0, corG = 0, corB = 0;
    float escala = 1;
    float angulo = 0;
    FloatBuffer vetorVertices = null;

    public Forma()
    {
        defineForma();
    }

    public void setPosicao(int x, int y)
    {
        posX = x;
        posY = y;
    }

    public void setCor(int r, int g, int b)
    {
        corR = r;
        corG = g;
        corB = b;
    }

    public void setAngulo(float ang)
    {
        angulo = ang;
    }

    public void setEscala(float esc)
    {
        escala = esc;
    }

    public float getX()
    {
        return posX;
    }

    public float getY()
    {
        return posY;
    }

    public float getAngulo()
    {
        return angulo;
    }

    public float getEscala()
    {
        return escala;
    }

    //Metodo que transforma o vetor de coordenadas java me FloatBuffer
    FloatBuffer retornaVetorC(float[] vetorJava)
    {
        //Aloca memoria necessaria para as coordenadas do desenho
        //encapsula o vetor java na classe FloatBuffer
        ByteBuffer vetorBytes =
                ByteBuffer.allocateDirect(vetorJava.length * 4);
        vetorBytes.order(ByteOrder.nativeOrder());

        FloatBuffer vetorC = vetorBytes.asFloatBuffer();
        vetorC.clear();
        vetorC.put(vetorJava);
        vetorC.flip();

        return vetorC;
    }

    public abstract void desenha(GL10 vrOpenGL);

    public abstract void defineForma();
}

