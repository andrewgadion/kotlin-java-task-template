package readCode;

import kotlin.NotImplementedError;

interface Disk {
    byte [] readSector(long sectorNumber);
}

class ImproveCode {
    public boolean compareDisks(Disk disk1, Disk disk2, long startSector, long sectorsCount)
    {
        for (long i = 0; i < sectorsCount; i++)
        {
            if (!compareTwoSectors(startSector + i, disk1, disk2))
                return false;
        }

        return true;
    }


    public boolean compareTwoSectors(long sectorNumber, Disk disk1, Disk disk2)
    {
        byte[] sectorByteArray1 = disk1.readSector(sectorNumber);
        byte[] sectorByteArray2 = disk2.readSector(sectorNumber);

        return compareByteArrays(sectorByteArray1, sectorByteArray2);
    }


    public static boolean compareByteArrays(byte[] array1, byte[] array2) {
        throw new NotImplementedError();
    }
}