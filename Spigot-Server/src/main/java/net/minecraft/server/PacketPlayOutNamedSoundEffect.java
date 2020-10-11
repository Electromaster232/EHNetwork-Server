package net.minecraft.server;

import org.apache.commons.lang3.Validate;
import java.util.HashMap;
import java.util.HashSet;

public class PacketPlayOutNamedSoundEffect
    implements Packet
{
    private static HashMap<String, String> sounds = new HashMap<String, String>();

    static {
        sounds.put("ambient.cave.cave", "ambient.cave");
        sounds.put("ambient.weather.rain", "weather.rain.above");
        sounds.put("ambient.weather.thunder", "entity.lightning.thunder");
        sounds.put("dig.cloth", "block.cloth.place");
        sounds.put("dig.grass", "block.grass.break");
        sounds.put("dig.gravel", "block.gravel.break");
        sounds.put("dig.sand", "block.sand.place");
        sounds.put("dig.snow", "block.snow.place");
        sounds.put("dig.stone", "block.glass.place");
        sounds.put("dig.wood", "block.wood.place");
        sounds.put("fire.fire", "entity.blaze.burn");
        sounds.put("fire.ignite", "item.flintandsteel.use");
        sounds.put("fireworks.blast", "entity.firework.blast");
        sounds.put("fireworks.blast_far", "entity.firework.blast_far");
        sounds.put("fireworks.largeBlast", "entity.firework.large_blast");
        sounds.put("fireworks.largeBlast_far", "entity.firework.large_blast_far");
        sounds.put("fireworks.launch", "entity.firework.launch");
        sounds.put("fireworks.twinkle", "entity.firework.twinkle");
        sounds.put("fireworks.twinkle_far", "entity.firework.twinkle_far");
        sounds.put("game.hostile.swim", "entity.player.swim");
        sounds.put("game.neutral.die", "entity.player.hurt");
        sounds.put("game.neutral.hurt.fall.small", "entity.generic.small_fall");
        sounds.put("game.neutral.swim.splash", "entity.generic.splash");
        sounds.put("game.player.hurt.fall.big", "entity.generic.big_fall");
        sounds.put("game.potion.smash", "block.glass.break");
        sounds.put("game.tnt.primed", "entity.tnt.primed");
        sounds.put("item.fireCharge.use", "item.firecharge.use");
        sounds.put("liquid.lava", "block.lava.ambient");
        sounds.put("liquid.lavapop", "block.lava.pop");
        sounds.put("liquid.water", "block.water.ambient");
        sounds.put("minecart.base", "entity.minecart.riding");
        sounds.put("minecart.inside", "entity.minecart.inside");
        sounds.put("mob.bat.death", "entity.bat.death");
        sounds.put("mob.bat.hurt", "entity.bat.hurt");
        sounds.put("mob.bat.idle", "entity.bat.ambient");
        sounds.put("mob.bat.takeoff", "entity.bat.takeoff");
        sounds.put("mob.blaze.breathe", "entity.blaze.ambient");
        sounds.put("mob.blaze.death", "entity.blaze.death");
        sounds.put("mob.blaze.hit", "entity.blaze.hurt");
        sounds.put("mob.cat.hitt", "entity.cat.hurt");
        sounds.put("mob.cat.meow", "entity.cat.ambient");
        sounds.put("mob.cat.purr", "entity.cat.purr");
        sounds.put("mob.cat.purreow", "entity.cat.purreow");
        sounds.put("mob.chicken.hurt", "entity.chicken.hurt");
        sounds.put("mob.chicken.plop", "entity.chicken.egg");
        sounds.put("mob.chicken.say", "entity.chicken.ambient");
        sounds.put("mob.chicken.step", "entity.chicken.step");
        sounds.put("mob.cow.hurt", "entity.cow.hurt");
        sounds.put("mob.cow.say", "entity.cow.ambient");
        sounds.put("mob.cow.step", "entity.cow.step");
        sounds.put("mob.creeper.death", "entity.creeper.death");
        sounds.put("mob.creeper.say", "entity.creeper.hurt");
        sounds.put("mob.enderdragon.end", "entity.enderdragon.death");
        sounds.put("mob.enderdragon.growl", "entity.enderdragon.growl");
        sounds.put("mob.enderdragon.hit", "entity.enderdragon.hurt");
        sounds.put("mob.enderdragon.wings", "entity.enderdragon.flap");
        sounds.put("mob.endermen.death", "entity.endermen.death");
        sounds.put("mob.endermen.hit", "entity.endermen.hurt");
        sounds.put("mob.endermen.idle", "entity.endermen.ambient");
        sounds.put("mob.endermen.portal", "entity.endermen.teleport");
        sounds.put("mob.endermen.scream", "entity.endermen.scream");
        sounds.put("mob.endermen.stare", "entity.endermen.stare");
        sounds.put("mob.ghast.charge", "entity.ghast.warn");
        sounds.put("mob.ghast.death", "entity.ghast.death");
        sounds.put("mob.ghast.moan", "entity.ghast.ambient");
        sounds.put("mob.ghast.scream", "entity.ghast.hurt");
        sounds.put("mob.guardian.attack", "entity.guardian.attack");
        sounds.put("mob.guardian.curse", "entity.elder_guardian.curse");
        sounds.put("mob.guardian.death", "entity.guardian.death");
        sounds.put("mob.guardian.elder.death", "entity.elder_guardian.death");
        sounds.put("mob.guardian.elder.hit", "entity.elder_guardian.hurt");
        sounds.put("mob.guardian.elder.idle", "entity.elder_guardian.ambient");
        sounds.put("mob.guardian.flop", "entity.guardian.flop");
        sounds.put("mob.guardian.hit", "entity.guardian.hurt");
        sounds.put("mob.guardian.idle", "entity.guardian.ambient");
        sounds.put("mob.guardian.land.death", "entity.guardian.death_land");
        sounds.put("mob.guardian.land.hit", "entity.elder_guardian.hurt_land");
        sounds.put("mob.guardian.land.idle", "entity.elder_guardian.ambient_land");
        sounds.put("mob.horse.angry", "entity.horse.angry");
        sounds.put("mob.horse.armor", "entity.horse.armor");
        sounds.put("mob.horse.breathe", "entity.horse.breathe");
        sounds.put("mob.horse.death", "entity.horse.death");
        sounds.put("mob.horse.donkey.angry", "entity.donkey.angry");
        sounds.put("mob.horse.donkey.death", "entity.mule.death");
        sounds.put("mob.horse.donkey.hit", "entity.donkey.hurt");
        sounds.put("mob.horse.donkey.idle", "entity.donkey.ambient");
        sounds.put("mob.horse.gallop", "entity.horse.gallop");
        sounds.put("mob.horse.hit", "entity.horse.hurt");
        sounds.put("mob.horse.idle", "entity.horse.ambient");
        sounds.put("mob.horse.jump", "entity.horse.jump");
        sounds.put("mob.horse.land", "entity.horse.land");
        sounds.put("mob.horse.leather", "entity.horse.saddle");
        sounds.put("mob.horse.skeleton.death", "entity.skeleton_horse.death");
        sounds.put("mob.horse.skeleton.hit", "entity.skeleton_horse.hurt");
        sounds.put("mob.horse.skeleton.idle", "entity.skeleton_horse.ambient");
        sounds.put("mob.horse.soft", "entity.horse.step");
        sounds.put("mob.horse.wood", "entity.horse.step_wood");
        sounds.put("mob.horse.zombie.death", "entity.zombie_horse.death");
        sounds.put("mob.horse.zombie.hit", "entity.zombie_horse.hurt");
        sounds.put("mob.horse.zombie.idle", "entity.zombie_horse.ambient");
        sounds.put("mob.irongolem.death", "entity.irongolem.death");
        sounds.put("mob.irongolem.hit", "entity.irongolem.hurt");
        sounds.put("mob.irongolem.throw", "entity.irongolem.attack");
        sounds.put("mob.irongolem.walk", "entity.irongolem.step");
        sounds.put("mob.magmacube.big", "entity.magmacube.squish");
        sounds.put("mob.magmacube.jump", "entity.magmacube.jump");
        sounds.put("mob.magmacube.small", "entity.small_magmacube.squish");
        sounds.put("mob.pig.death", "entity.pig.death");
        sounds.put("mob.pig.say", "entity.pig.hurt");
        sounds.put("mob.pig.step", "entity.pig.step");
        sounds.put("mob.rabbit.death", "entity.rabbit.death");
        sounds.put("mob.rabbit.hop", "entity.rabbit.jump");
        sounds.put("mob.rabbit.hurt", "entity.rabbit.hurt");
        sounds.put("mob.rabbit.idle", "entity.rabbit.ambient");
        sounds.put("mob.sheep.say", "entity.sheep.death");
        sounds.put("mob.sheep.shear", "entity.sheep.shear");
        sounds.put("mob.sheep.step", "entity.sheep.step");
        sounds.put("mob.silverfish.hit", "entity.endermite.hurt");
        sounds.put("mob.silverfish.kill", "entity.silverfish.death");
        sounds.put("mob.silverfish.say", "entity.silverfish.ambient");
        sounds.put("mob.silverfish.step", "entity.endermite.step");
        sounds.put("mob.skeleton.death", "entity.skeleton.death");
        sounds.put("mob.skeleton.hurt", "entity.skeleton.hurt");
        sounds.put("mob.skeleton.say", "entity.skeleton.ambient");
        sounds.put("mob.skeleton.step", "entity.skeleton.step");
        sounds.put("mob.slime.attack", "entity.slime.attack");
        sounds.put("mob.slime.big", "block.slime.place");
        sounds.put("mob.slime.small", "block.slime.fall");
        sounds.put("mob.spider.death", "entity.spider.death");
        sounds.put("mob.spider.say", "entity.spider.ambient");
        sounds.put("mob.spider.step", "entity.spider.step");
        sounds.put("mob.villager.death", "entity.villager.death");
        sounds.put("mob.villager.haggle", "entity.villager.trading");
        sounds.put("mob.villager.hit", "entity.villager.hurt");
        sounds.put("mob.villager.idle", "entity.villager.ambient");
        sounds.put("mob.villager.no", "entity.villager.no");
        sounds.put("mob.villager.yes", "entity.villager.yes");
        sounds.put("mob.wither.death", "entity.wither.death");
        sounds.put("mob.wither.hurt", "entity.wither.hurt");
        sounds.put("mob.wither.idle", "entity.wither.ambient");
        sounds.put("mob.wither.shoot", "entity.wither.shoot");
        sounds.put("mob.wither.spawn", "entity.wither.spawn");
        sounds.put("mob.wolf.bark", "entity.wolf.ambient");
        sounds.put("mob.wolf.death", "entity.wolf.death");
        sounds.put("mob.wolf.growl", "entity.wolf.growl");
        sounds.put("mob.wolf.hurt", "entity.wolf.hurt");
        sounds.put("mob.wolf.panting", "entity.wolf.pant");
        sounds.put("mob.wolf.shake", "entity.wolf.shake");
        sounds.put("mob.wolf.step", "entity.wolf.step");
        sounds.put("mob.wolf.whine", "entity.wolf.whine");
        sounds.put("mob.zombie.death", "entity.zombie.death");
        sounds.put("mob.zombie.hurt", "entity.zombie.hurt");
        sounds.put("mob.zombie.infect", "entity.zombie.infect");
        sounds.put("mob.zombie.metal", "entity.zombie.attack_iron_door");
        sounds.put("mob.zombie.remedy", "entity.zombie.cure");
        sounds.put("mob.zombie.say", "entity.zombie.ambient");
        sounds.put("mob.zombie.step", "entity.zombie.step");
        sounds.put("mob.zombie.unfect", "entity.zombie.unfect");
        sounds.put("mob.zombie.wood", "entity.zombie.attack_door_wood");
        sounds.put("mob.zombie.woodbreak", "entity.zombie.break_door_wood");
        sounds.put("mob.zombiepig.zpig", "entity.zombie_pig.ambient");
        sounds.put("mob.zombiepig.zpigangry", "entity.zombie_pig.angry");
        sounds.put("mob.zombiepig.zpigdeath", "entity.zombie_pig.death");
        sounds.put("mob.zombiepig.zpighurt", "entity.zombie_pig.hurt");
        sounds.put("music.game", "music.game");
        sounds.put("music.game.creative", "music.creative");
        sounds.put("music.game.end", "music.end");
        sounds.put("music.game.end.credits", "music.credits");
        sounds.put("music.game.end.dragon", "music.dragon");
        sounds.put("music.game.nether", "music.nether");
        sounds.put("music.menu", "music.menu");
        sounds.put("note.bassattack", "block.note.bass");
        sounds.put("note.bd", "block.note.basedrum");
        sounds.put("note.baseattack", "block.note.basedrum");
        sounds.put("note.harp", "block.note.harp");
        sounds.put("note.pling", "block.note.harp");
        sounds.put("note.hat", "block.note.hat");
        sounds.put("note.snare", "block.note.snare");
        sounds.put("portal.portal", "block.portal.ambient");
        sounds.put("portal.travel", "block.portal.travel");
        sounds.put("portal.trigger", "block.portal.trigger");
        sounds.put("random.anvil_break", "block.anvil.destroy");
        sounds.put("random.anvil_land", "block.anvil.place");
        sounds.put("random.anvil_use", "block.anvil.use");
        sounds.put("random.bow", "entity.firework.shoot");
        sounds.put("random.bowhit", "block.tripwire.detach");
        sounds.put("random.break", "entity.item.break");
        sounds.put("random.burp", "entity.player.burp");
        sounds.put("random.chestclosed", "block.enderchest.close");
        sounds.put("random.chestopen", "block.chest.open");
        sounds.put("random.click", "block.stone_pressureplate.click_on");
        sounds.put("random.door_close", "block.trapdoor.open");
        sounds.put("random.drink", "entity.generic.drink");
        sounds.put("random.eat", "entity.generic.eat");
        sounds.put("random.explode", "entity.lightning.impact");
        sounds.put("random.fizz", "entity.generic.extinguish_fire");
        sounds.put("random.levelup", "entity.player.levelup");
        sounds.put("random.orb", "entity.experience_orb.touch");
        sounds.put("random.pop", "entity.item.pickup");
        sounds.put("random.splash", "entity.bobber.splash");
        sounds.put("random.successful_hit", "entity.arrow.successful_hit");
        sounds.put("random.wood_click", "block.wood_button.click_off");
        sounds.put("records.11", "record.11");
        sounds.put("records.13", "record.13");
        sounds.put("records.blocks", "record.blocks");
        sounds.put("records.cat", "record.cat");
        sounds.put("records.chirp", "record.chirp");
        sounds.put("records.far", "record.far");
        sounds.put("records.mall", "record.mall");
        sounds.put("records.mellohi", "record.mellohi");
        sounds.put("records.stal", "record.stal");
        sounds.put("records.strad", "record.strad");
        sounds.put("records.wait", "record.wait");
        sounds.put("records.ward", "record.ward");
        sounds.put("step.cloth", "block.cloth.fall");
        sounds.put("step.grass", "block.grass.step");
        sounds.put("step.gravel", "block.gravel.hit");
        sounds.put("step.ladder", "block.ladder.hit");
        sounds.put("step.sand", "block.sand.hit");
        sounds.put("step.snow", "block.snow.fall");
        sounds.put("step.stone", "block.stone.hit");
        sounds.put("step.wood", "block.wood.step");
        sounds.put("tile.piston.in", "block.piston.contract");
        sounds.put("tile.piston.out", "block.piston.extend");
    }

    public String a;
    public int b;
    public int c;
    public int d;
    public float e;
    public int f;

    public PacketPlayOutNamedSoundEffect()
    {
        c = 0x7fffffff;
    }

    public PacketPlayOutNamedSoundEffect(String s, double d1, double d2, double d3,
            float f1, float f2)
    {
        c = 0x7fffffff;
        Validate.notNull(s, "name", new Object[0]);

        a = s;
        b = (int)(d1 * 8D);
        c = (int)(d2 * 8D);
        d = (int)(d3 * 8D);
        e = f1;
        f = (int)(f2 * 63F);
        f2 = MathHelper.a(f2, 0.0F, 255F);
    }

    public void a(PacketDataSerializer packetdataserializer)
    {
        a = packetdataserializer.c(256);
        b = packetdataserializer.readInt();
        c = packetdataserializer.readInt();
        d = packetdataserializer.readInt();
        e = packetdataserializer.readFloat();
        f = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer)
    {
        String toPlay = a;
        if (packetdataserializer.version > 47 && sounds.containsKey(toPlay))
            toPlay = sounds.get(toPlay);

        packetdataserializer.a(toPlay);
        if (packetdataserializer.version > 47)
            packetdataserializer.b(0);
        packetdataserializer.writeInt(b);
        packetdataserializer.writeInt(c);
        packetdataserializer.writeInt(d);
        packetdataserializer.writeFloat(e);
        packetdataserializer.writeByte(f);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout)
   {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener)
    {
        a((PacketListenerPlayOut)packetlistener);
    }
}