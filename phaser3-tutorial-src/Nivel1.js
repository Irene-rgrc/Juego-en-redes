export class Game extends Phaser.Scene{ 
//Exportamos el nivel para poder usarse fuera. Es una clase de javaScript, extiende de Phaser.Scene (Punto de partida para crear escenas del juego)

    constructor(){
        super({key:"Nivel1"}); //Inicializar la clase. Key indica su nombre para poder usarse,es una valor clave unico; cada escena debe tener uno distinto.
    
         
    }

   
    preload(){
       
        this.load.image('ground', 'assetsEjemplo/platform.png');
        this.load.image('background', 'assets/Concept_Subterraneo_principal.jpeg');
        //this.load.multiatlas('Seraphine','assets/spritesheet.json','assets/spritesheet.png');
        //this.load.path='./assets/';
        //this.load.json('SeraphineWalk','spritesheet.json');
        //history.load.atlas('spritesSeraphine','spritesheet.png')
        this.load.spritesheet('Seraphine', 'assets/spritesheet.png', { frameWidth: 857, frameHeight: 983 });
    }
    create(){
        this.add.image(400, 300, 'background');
        
        this.platform = this.physics.add.image(400,460,"ground").setScale(2);
        this.platform.body.allowGravity=false;

        //Seraphine = this.physics.add.sprite(100, 450, 'Seraphine').setScale(0.10,0.10);
        //Serpahine.setBounce(0.2);

        this.Seraphine = this.physics.add.sprite(100, 450, 'Seraphine').setScale(0.10,0.10);
        Seraphine.setBounce(0.2);
        Seraphine.setCollideWorldBounds(true);

        this.anims.create({
            key: 'left',
            frames: this.anims.generateFrameNumbers('Seraphine', { start: 0, end: 3 }),
            frameRate: 10,
            repeat: -1
        });

        this.anims.create({
            key: 'turn',
            frames: [ { key: 'Seraphine', frame: 4 } ],
            frameRate: 20
        });
    
        this.anims.create({
            key: 'right',
            frames: this.anims.generateFrameNumbers('Seraphine', { start: 5, end: 9 }),
            frameRate: 10,
            repeat: -1
        });
    
        //  Input Events
        cursors = this.input.keyboard.createCursorKeys();
        this.physics.add.collider(Seraphine, platform);

    }

    update ()
{
    if (gameOver)
    {
        return;
    }

    if (cursors.left.isDown)
    {
        this.Seraphine.setVelocityX(-160);
        this.Seraphine.anims.play('left', true);

    } else if(cursors.right.isDown)
    {
        this.Seraphine.setVelocityX(160);
        this.Seraphine.anims.play('right', true);
    }
    else if(cursors.up.isDown && player1.body.touching.down)
    {
        this.Seraphine.setVelocityY(-330);
    }
    else
    {
        this.Seraphine.setVelocityX(0);
        this.Seraphine.anims.play('turn');
    }
}
   

}

