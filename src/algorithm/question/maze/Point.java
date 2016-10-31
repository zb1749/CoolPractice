package algorithm.question.maze;

/**
 * 二维矩阵某元素的坐标
 *
 * Created by Kevin on 2016/10/31.
 */
class Point {
    // 第几行
    public int i;
    // 第几列
    public int j;
    // 已读取的邻接元素个数，0读取正上，1读取正右，2读取正下，3读取正左
    public int t;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

    // 该元素是否是死胡同(该元素的，正上，正右，正下，正左，四个方向都找不到终点)
    public boolean deadEnd() {
        return t > 3 ? true : false;
    }

    // 以某元素为中心，按顺时针方向，依次取该元素的，正上，正右，正下，正左，四个邻接元素
    // 如果超出边界，则返回null，如果这四个邻接元素都获取过，则返回null
    public Point getNeighBour() {
        Point point = null;

        switch (t) {
            // 正上元素
            case 0: {
                if (this.i > 0) {
                    point = new Point(this.i - 1, j);
                }
                break;
            }
            // 正右元素
            case 1: {
                point = new Point(i, j + 1);
                break;
            }
            // 正下元素
            case 2: {
                point = new Point(i + 1, j);
                break;
            }
            // 正左元素
            case 3: {
                point = new Point(i, j - 1);
                break;
            }
            default: {
                return point;
            }
        }

        t++;
        return point;
    }

    // 重写equals()方法
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }

        Point point = (Point) obj;
        if (point.i == this.i && point.j == this.j) {
            return true;
        } else {
            return false;
        }
    }

    // 重写hashCode()方法
    @Override
    public int hashCode() {
        return (String.valueOf(i) + String.valueOf(j)).hashCode();
    }
}
