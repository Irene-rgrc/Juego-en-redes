import {Game}from ".game.js"; //Importar las distintas escenas

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

var game=new Phaser.Game(config);
