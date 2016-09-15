package computacaografica.openglincial;

//Pacotes utilizados pela aplicacao
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Bundle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import computacaografica.openglincial.formas.Estrela;
import computacaografica.openglincial.formas.Quadrado;
import computacaografica.openglincial.formas.Triangulo;

//Classe principal de uma aplicacao Android
public class OpenGL extends Activity
{
    //Cria o objeto que representa a superficie de desenho
    GLSurfaceView superficieDesenho = null;
    Renderizador objetoDesenho = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Metodo chamado durante a criacao do aplicativo
        super.onCreate(savedInstanceState);

        //Instancia o objeto de desenho
        objetoDesenho = new Renderizador(this);

        //Instancia a superficie de desenho e a liga a tela
        superficieDesenho = new GLSurfaceView(this);

        //Liga o objeto de desenho na superficie de desenho
        superficieDesenho.setRenderer(objetoDesenho);

        //Publica a superficie de desenho na tela
        setContentView(superficieDesenho);
    }
}

class Renderizador implements GLSurfaceView.Renderer
{
    int altura=0;
    int largura=0;
    float angulo = 0;
    int iDirecaoAngulo = 1;
    OpenGL contexto = null;

    public Renderizador(OpenGL pContexto)
    {
        contexto = pContexto;
    }
    @Override
    public void onSurfaceCreated(GL10 vrOpengl, EGLConfig config)
    {
        //Metodo chamado no momento da criacao da superficie
        vrOpengl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 vrOpengl, int width, int height)
    {
        altura = height;
        largura  = width;

        //Metodo chamado na inicializacao do app
        //Metodo chamado sempre que houver rotacao de tela
        vrOpengl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        //Configura a janela de visualizacao
        //para o mesmo tamanho da tela
        vrOpengl.glViewport(0,0,width, height);

        //Seta a matriz de projecao para uso, carrega identidade
        //Configura o volume de renderizacao do tipo Orto (2D)
        vrOpengl.glMatrixMode(GL10.GL_PROJECTION);
        vrOpengl.glLoadIdentity();
        vrOpengl.glOrthof(0, width, 0, height, 1.0f, -1.0f);

        //Seta a matriz de modelos e camera para uso
        //configura a matriz identidade
        vrOpengl.glMatrixMode(GL10.GL_MODELVIEW);
        vrOpengl.glLoadIdentity();

        float[] vertices = {-100.0f, -100f,
                -100.0f, 100.0f,
                100.0f, -100.0f,
                100.0f, 100.0f};

        vrOpengl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        vrOpengl.glEnable(GL10.GL_TEXTURE_2D);
        vrOpengl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        vrOpengl.glEnable(GL10.GL_BLEND);
        vrOpengl.glEnable(GL10.GL_ALPHA_TEST);
        vrOpengl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);


        vrOpengl.glVertexPointer(2, GL10.GL_FLOAT, 0, retornaVetorC(vertices));
    }

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

    public int carregaTextura(GL10 vrOpenGl, int codImagem)
    {
        int[] vetTexturas = new int[1];

        //carrega um bitmap na memoria RAM
        Bitmap vrImagem = BitmapFactory.decodeResource(contexto.getResources(), codImagem);

        //cria UMA area de memoria na VRAM(memoria de video)
        vrOpenGl.glGenTextures(1, vetTexturas, 0);

        //COPIA A IMAGEM DA RAM PARA VRAM
        //ASSINA COM O GlBind
        vrOpenGl.glBindTexture(GL10.GL_TEXTURE_2D, vetTexturas[0]);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, vrImagem, 0);

        //configura os filtros de imagem para encolhimento e esticamento de imagem
        vrOpenGl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        vrOpenGl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

        //deleta a imagem da RAM
        vrImagem.recycle();

        //retorna o identificador dessa textura
        return vetTexturas[0];
    }

    @Override
    public void onDrawFrame(GL10 vrOpengl)
    {
        vrOpengl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        vrOpengl.glLoadIdentity();
        vrOpengl.glColor4f(1, 1, 1, 1);
        vrOpengl.glTranslatef(largura / 2, altura / 2, 0);
//        vrOpengl.glRotatef(anguloRoda, 0, 0, 1);
        vrOpengl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

    }
}




