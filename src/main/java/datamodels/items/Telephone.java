package datamodels.items;

import datamodels.Player;

public class Telephone extends Item {
    private final Axe axe;
    private boolean hasBeenAxed = false;

    public Telephone(String name,
                     String description,
                     boolean canPickUp,
                     String probeMessage,
                     Axe axe) {
        super(name, description, canPickUp, probeMessage);
        this.axe = axe;
    }

    @Override
    public void interact(Player player) {
        if (player.isHolding(axe) && !hasBeenAxed) {
            System.out.println("You smash the axe into the glass case, freeing the telephone.");
            description = "a telephone lies here amidst a bunch of glass shards";
            probeMessage = "The phone cord has been cut by your carelessness with the axe, " +
                    "but there appears to be one voice mail.  You press play, and hear the following: " +
                    "Friends, Romans, countrymen, lend me your ears...";
            System.out.println(probeMessage);
            player.heardVoiceMail();
            hasBeenAxed = true;
        } else {
            super.probe();
        }
    }
}
