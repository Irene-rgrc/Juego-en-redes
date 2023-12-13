export class Game extends Phaser.Scene{ 
//Exportamos el nivel para poder usarse fuera. Es una clase de javaScript, extiende de Phaser.Scene (Punto de partida para crear escenas del juego)

    constructor(){
        super({key:'Nivel1'}); //Inicializar la clase. Key indica su nombre para poder usarse,es una valor clave unico; cada escena debe tener uno distinto.
        
    }

   
    preload(){
       
        this.load.image('ground', 'assetsEjemplo/platform.png');
        this.load.image('background', 'assets/Concept_Subterraneo_principal.jpeg');
        this.load.spritesheet('Seraphine', 'assets/spritesheet.png', { frameWidth: 857, frameHeight: 983 });
    }
    create(){
        
        
        this.add.image(400, 300, 'background').setScale();
        this.platform = this.physics.add.image(400,460,"ground");
        this.platform.body.allowGravity=false;

        //Seraphine = this.physics.add.sprite(100, 450, 'Seraphine').setScale(0.10,0.10);
        //Serpahine.setBounce(0.2);

        
        this.Seraphine = this.physics.add.sprite(100, 450, 'Seraphine').setScale(0.10,0.10);
        this.Seraphine.setBounce(0.2);
        this.Seraphine.setCollideWorldBounds(true);

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
        this.cursors = this.input.keyboard.createCursorKeys();
        this.physics.add.collider(this.Seraphine, this.platform);

    }

    update ()
{
    

    if (this.cursors.left.isDown)
    {
        this.Seraphine.setVelocityX(-160);
        this.Seraphine.anims.play('left', true);

    } else if(this.cursors.right.isDown)
    {
        this.Seraphine.setVelocityX(160);
        this.Seraphine.anims.play('right', true);
    }
    else if(this.cursors.up.isDown && this.Seraphine.body.touching.down)
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

