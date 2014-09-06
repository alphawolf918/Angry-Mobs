package angrymobs.managers;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundManager {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		event.manager.addSound("awam:mob/ghost/say.ogg");
		event.manager.addSound("awam:mob/ghost/hurt.ogg");
		event.manager.addSound("awam:mob/ghost/death.ogg");
		event.manager.addSound("awam:mob/ghost/change.ogg");
	}
}