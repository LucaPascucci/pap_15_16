package ass03.sol;

/**
 * Implementation of a pair -
 *
 * @param <L> type of the left component of the pair
 * @param <R> type of the right component of the pair
 */
class Pair<L, R> {

	private final L left;
	private final R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair))
			return false;
		Pair<L,R> pairo = (Pair<L,R>) o;
		return this.left.equals(pairo.getLeft()) && this.right.equals(pairo.getRight());
	}

	public String toString() {
		return "( "+left+", "+right+")";
	}
}
