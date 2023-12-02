import {Game}from "./Nivel1.js"; //Importar las distintas escenas

const config = {

    type: Phaser.AUTO,
    width: 800,
    height: 600,
    scene:[Game], //Para configurar las distintas partes del juego: menus, niveles, creditos...

    physics: {
        default: 'arcade',
        arcade: {
            gravity: { y: 300 },
            debug: false
        }
    }
}

var Nivel1=new Phaser.Game(config); //Para crear el juego como tal, le pasamos la configuracion

