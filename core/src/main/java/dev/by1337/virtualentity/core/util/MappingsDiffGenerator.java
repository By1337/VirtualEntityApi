package dev.by1337.virtualentity.core.util;

import org.by1337.blib.nbt.NBT;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.nbt.impl.ListNBT;
import org.by1337.blib.nbt.impl.StringNBT;

public class MappingsDiffGenerator {

    public static String createDiff(CompoundTag newMappings, CompoundTag oldMappings) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        diff(newMappings, oldMappings, sb, 4);
        sb.append("}");
        return sb.toString();
    }

    private static void diff(CompoundTag newMappings, CompoundTag oldMappings, StringBuilder out, int spaces) {
        String tab = " ".repeat(spaces);

        for (String key : newMappings.getTags().keySet()) {
            NBT newTag = newMappings.get(key);
            NBT oldTag = oldMappings.get(key);

            if (!oldMappings.has(key)) {
                append("++", key, newTag, out, spaces);
            } else if (newTag instanceof CompoundTag cNew && oldTag instanceof CompoundTag cOld) {
                out.append("  ").append(tab).append(key).append(": {\n");
                diff(cNew, cOld, out, spaces + 4);
                out.append("  ").append(tab).append("}\n");
            } else if (newTag instanceof StringNBT sNew && oldTag instanceof StringNBT sOld) {
                if (!sNew.getValue().equals(sOld.getValue())) {
                    out.append("~~").append(tab).append(key).append(": ").append(sNew.getValue()).append("\n");
                }
            }
        }

        for (String key : oldMappings.getTags().keySet()) {
            if (!newMappings.has(key)) {
                NBT oldTag = oldMappings.get(key);
                append("--", key, oldTag, out, spaces);
            }
        }
    }

    private static void append(String prefix, String key, NBT tag, StringBuilder out, int spaces) {
        String tab = " ".repeat(spaces);
        if (tag instanceof CompoundTag compound) {
            out.append(prefix).append(tab).append(key).append(": {\n");
            for (String childKey : compound.getTags().keySet()) {
                append(prefix, childKey, compound.get(childKey), out, spaces + 4);
            }
            out.append(prefix).append(tab).append("}\n");
        } else if (tag instanceof StringNBT stringTag) {
            out.append(prefix).append(tab).append(key).append(": ").append(stringTag.getValue()).append("\n");
        } else if (tag instanceof ListNBT list) {
            if (list.isEmpty()) {
                out.append(prefix).append(tab).append(key).append(": []\n");
            } else {
                out.append(prefix).append(tab).append(key).append(": [\n");
                for (NBT nbt : list) {
                    append(prefix, "", nbt, out, spaces + 4);
                    out.setLength(out.length() - 1);
                    out.append(",\n");
                }
                out.append(prefix).append(tab).append("]\n");
            }
        } else {
            out.append(prefix).append(tab).append(key).append(": ~ ").append("\n");
        }
    }
}
