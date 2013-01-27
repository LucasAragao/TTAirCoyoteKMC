
//Source file: C:\\_COYOTEMPEG7\\Rose_Player_API\\CoyotePlayer\\src\\presentation\\Player.java

package presentation;



/**
 * Classe respons�vel por gerenciar o Player. O usu�rio da API  precisa somente
 * dela para utilizar o player.
 */
public abstract class APlayer
{

  private AControlPlayer theAControlPlayer;


   public void setControlPlayer(AControlPlayer controlPlayer)
   {
      this.theAControlPlayer = controlPlayer;
   }

   /**
    * @return presentation.AControlPlayer
    * @roseuid 41BFA2FE01A5
    */
   public AControlPlayer getControlPlayer()
   {
    return this.theAControlPlayer;
   }

}
