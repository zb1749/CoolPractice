package dbutil.common;


/**
 * 描述一个连续的区间
 * @param <T>
 */
public abstract class ContinuedRange<T extends Comparable<T>> implements Range<T> {
	private static final long serialVersionUID = 1L;
	

	final public boolean isContinuous(){
		return true;
	}
	
	/**
	 * 获得区间的结束点
	 * @return
	 */
	public abstract T getEnd();
	
	/**
	 * 获取区间的开始点
	 * @return
	 */
	public abstract T getStart();
	
	public boolean include(ContinuedRange<T> obj){
		return false;
	}
	
	public boolean nextTo(ContinuedRange<T> obj){
		return false;
	}
	
	/**
	 * 本区间与目标区间是否重合
	 * @param obj
	 * @return
	 */
	public boolean overlapping(ContinuedRange<T> obj){
		if(obj.getEnd().compareTo(this.getStart())<0 || obj.getStart().compareTo(this.getEnd())>0){
			return false;
		}
		
		if(obj.getEnd().compareTo(this.getStart())==0){
			if(obj.isEndIndexInclusive() && this.isBeginIndexInclusive()){
				return true;
			}else{
				return false;
			}
		}
		
		if(obj.getStart().compareTo(this.getEnd())==0){
			if(obj.isBeginIndexInclusive() && this.isEndIndexInclusive()){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 如果指定的值位于区间之外，则将区间扩展到那个点上
	 * @param obj
	 */
	public abstract void extendTo(T obj);

	public final T getGreatestValue() {
		return getEnd();
	}

	public final T getLeastValue() {
		return getStart();
	}

	public final boolean contains(T obj) {
		//Assert.notNull(obj);
		if(this.isBeginIndexInclusive()){
			if(obj.compareTo(this.getStart())<0){
				return true;
			}
		}else{
			if(obj.compareTo(this.getStart())<=0){
				return true;
			}
		}
		if(this.isEndIndexInclusive()){
			if(obj.compareTo(this.getEnd())>0){
				return true;
			}
		}else{
			if(obj.compareTo(this.getEnd())>=0){
				return true;
			}
		}
		return false;
	}

	/**
	 * 最小值是否包含在范围内？ 即是否开区间
	 * @return
	 */
	public abstract boolean isEndIndexInclusive();
	
	/**
	 * 最大值是否包含在范围内？ 即是否开区间
	 * @return
	 */
	public abstract boolean isBeginIndexInclusive();
	
	
}
