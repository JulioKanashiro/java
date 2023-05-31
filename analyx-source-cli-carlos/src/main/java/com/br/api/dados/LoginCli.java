package com.br.api.dados;

import com.br.api.banco.jdbc.Usuario;
import com.br.api.banco.jdbc.controller.EspecificacaoMaquinaController;
import com.br.api.banco.jdbc.controller.UsuarioController;
import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jukan
 */
public class LoginCli {

    public static void main(String[] args) throws InterruptedException, IOException {
        UsuarioController usuarioDAO = new UsuarioController();
        EspecificacaoMaquinaController emDAO = new EspecificacaoMaquinaController();
        Looca looca = new Looca();
        Logger log = new Logger("logAnalyx.txt");
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.println("Email:");
        String email = sc1.nextLine();
        System.out.println("Senha:");
        String senha = sc2.nextLine();

        try {
            Usuario user = usuarioDAO.entrarAzure(email, senha);
            String hostName = looca.getRede().getParametros().getHostName();
            emDAO.cadastroDaMaquina(hostName, user.getFuncionario());
            emDAO.cadastroDaMaquinaLocal(hostName);
            
            
            log.logInfo("Login efetuado user: " + email + " Maquina: " + emDAO.getEspecificacaoMaquinaPorHostNameAzure(hostName));
            ApiCli apiCli = new ApiCli();
            apiCli.startApp();
        } catch (Exception e) {
            log.logWarning("Informações de login incorretas, user: " + email);
            System.out.println("erro ->" + e.getMessage());
            e.printStackTrace();
        }
        log.close();

    }
}
