package com.projeto.poo;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class EmpresaService {
    public static Map<String,Empresa> empresas = new HashMap<>();

    public void cadastraEmpresa(String nome,String email,String pais,String estado,String cep,String sobre,String cnpj,String username,String senha) throws LinketinderException {

        if(empresas.get(cnpj) == null) {

            empresas.put(cnpj, new Empresa(nome, email, pais, estado, cep, sobre, cnpj, username, senha));

            System.out.println("Cadastro efetuado");

        } else {
            JOptionPane.showMessageDialog(null, "Empresa com este CNPJ já cadastrada.","Atenção",JOptionPane.WARNING_MESSAGE);
            throw new LinketinderException("Empresa com este CNPJ já cadastrada.");
        }
    }
    public Empresa getEmpresa(String cnpj){
        return empresas.get(cnpj);
    }

    public void deletaEmpresa(String cnpj){
        empresas.remove(cnpj);
        System.out.println("Removido");
    }
}
