/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectKMC {

    private String arqName;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;

    public ObjectKMC(String nomeArq) {
        this.arqName = nomeArq;
        outStream = null;
        inStream = null;
    }

    public boolean reset() {
        try {
            inStream = new ObjectInputStream(new FileInputStream(arqName));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public Serializable readOBJ() {

        if (inStream == null) {
            return null;
        } else {
            try {
                return (Serializable) inStream.readObject();
            } catch (IOException e) {
                return null;
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
    }

    public void writeOBJ(Serializable obj) {
        try {
            if (outStream == null) {
                System.out.println("Erro: O arquivo não foi aberto");
            } else {
                outStream.writeObject(obj);
               // System.out.println("Arquivo gravado com sucesso.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gravar o arquivo");
        }
    }

    public void rewrite() {
        if (outStream != null) {
            closeFile();
        }
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(arqName));
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(0);
        }
    }

    public void closeFile() {
        try {
            if (outStream != null) {
                outStream.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo");
        }
        outStream = null;
        inStream = null;
    }

    public void flush() throws IOException {
        outStream.flush();
    }
}
