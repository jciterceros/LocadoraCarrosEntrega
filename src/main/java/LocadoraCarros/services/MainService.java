package LocadoraCarros.services;

import LocadoraCarros.view.MenuPrincipal;

public class MainService {

    public void iniciarSistema() {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

}
