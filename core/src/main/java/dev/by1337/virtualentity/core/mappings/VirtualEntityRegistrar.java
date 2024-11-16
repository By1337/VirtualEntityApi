package dev.by1337.virtualentity.core.mappings;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.VirtualEntityFactory;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.*;
import dev.by1337.virtualentity.core.virtual.animal.*;
import org.by1337.blib.util.Version;

public class VirtualEntityRegistrar {

    public static void register(){
        VirtualEntityFactory factory = VirtualEntityApi.getFactory();

        factory.register(VirtualEntityType.ARMOR_STAND, VirtualArmorStandImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.BAT, VirtualBatImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.BEE, VirtualBeeImpl::new, Version.V1_16_5);
    }
}
