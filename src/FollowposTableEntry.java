import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class FollowposTableEntry
{
    private SortedMap<Integer, FollowposTableEntry> followposTableEntries = new TreeMap<>();
    public final int position;
    public final String symbol;
    public final Set<Integer> followpos = new HashSet<>();
    public FollowposTableEntry(int position, String symbol)
    {
        this.position = position;
        this.symbol = symbol;
    }
}
