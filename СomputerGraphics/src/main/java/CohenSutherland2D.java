import java.awt.*;
import java.awt.geom.Point2D;

public class CohenSutherland2D {

    private enum Position {
        LEFT (1),
        RIGHT (2),
        BOT (4),
        TOP (8);

        private final int code;
        Position(int code) {
            this.code = code;
        }

        public int code()   { return code; }
    }

    private Point points[];

    boolean CohenSutherland(Rectangle rectangle, Point a, Point b) {
        int code_a, code_b; /* ���� ������ ������� */
        int code; // ��� ����� �����
        Point c; /* ���� �� ����� */

        code_a = getCode(rectangle, a);
        code_b = getCode(rectangle, b);

        /* ���� ���� �� ����� ������� ��� �������������� */
        while ((code_a | code_b) != 0) {
            /* ���� ��� ����� � ����� ������� ��������������, �� ������� �� ���������� ������������� */
            if ((code_a & code_b) != 0)
                return true;

            /* �������� ����� c � ��������� ����� */
            if (code_a != 0) {
                code = code_a;
                c = a;
            } else {
                code = code_b;
                c = b;
            }

		/* ���� c ����� r, �� ����������� c �� ������ x = r->x_min
		   ���� c ������ r, �� ����������� c �� ������ x = r->x_max */
            if ((code & Position.LEFT.code()) != 0) {
                c.y += (a.y - b.y) * (rectangle.BottomLeft.x - c.x) / (a.x - b.x);
                c.x = rectangle.BottomLeft.x;
            } else if ((code & Position.RIGHT.code()) != 0) {
                c.y += (a.y - b.y) * (rectangle.TopRight.x - c.x) / (a.x - b.x);
                c.x = rectangle.TopRight.x;
            }/* ���� c ���� r, �� ����������� c �� ������ y = r->y_min
		    ���� c ���� r, �� ����������� c �� ������ y = r->y_max */
            else if ((code & Position.BOT.code()) != 0) {
                c.x += (a.x - b.x) * (rectangle.BottomLeft.y - c.y) / (a.y - b.y);
                c.y = rectangle.BottomLeft.y;
            } else if ((code & Position.TOP.code()) != 0) {
                c.x += (a.x - b.x) * (rectangle.TopRight.y - c.y) / (a.y - b.y);
                c.y = rectangle.TopRight.y;
            }

            /* ��������� ��� */
            if (code == code_a) {
                a = c;
                code_a = getCode(rectangle, a);
            } else {
                b = c;
                code_b = getCode(rectangle, b);
            }
        }

        /* ��� ���� ����� 0, ������������� ��� ����� � �������������� */
        return false;
    }

    boolean CohenSutherland(Rectangle rectangle, Segment s) {
        Point a = s._point1;
        Point b = s._point2;
        int code_a, code_b; /* ���� ������ ������� */
        int code; // ��� ����� �����
        Point c; /* ���� �� ����� */

        code_a = getCode(rectangle, a);
        code_b = getCode(rectangle, b);

        /* ���� ���� �� ����� ������� ��� �������������� */
        while ((code_a | code_b) != 0) {
            /* ���� ��� ����� � ����� ������� ��������������, �� ������� �� ���������� ������������� */
            if ((code_a & code_b) != 0)
                return true;

            /* �������� ����� c � ��������� ����� */
            if (code_a != 0) {
                code = code_a;
                c = a;
            } else {
                code = code_b;
                c = b;
            }

		/* ���� c ����� r, �� ����������� c �� ������ x = r->x_min
		   ���� c ������ r, �� ����������� c �� ������ x = r->x_max */
            if ((code & Position.LEFT.code()) != 0) {
                c.y += (a.y - b.y) * (rectangle.BottomLeft.x - c.x) / (a.x - b.x);
                c.x = rectangle.BottomLeft.x;
            } else if ((code & Position.RIGHT.code()) != 0) {
                c.y += (a.y - b.y) * (rectangle.TopRight.x - c.x) / (a.x - b.x);
                c.x = rectangle.TopRight.x;
            }/* ���� c ���� r, �� ����������� c �� ������ y = r->y_min
		    ���� c ���� r, �� ����������� c �� ������ y = r->y_max */
            else if ((code & Position.BOT.code()) != 0) {
                c.x += (a.x - b.x) * (rectangle.BottomLeft.y - c.y) / (a.y - b.y);
                c.y = rectangle.BottomLeft.y;
            } else if ((code & Position.TOP.code()) != 0) {
                c.x += (a.x - b.x) * (rectangle.TopRight.y - c.y) / (a.y - b.y);
                c.y = rectangle.TopRight.y;
            }

            /* ��������� ��� */
            if (code == code_a) {
                a = c;
                code_a = getCode(rectangle, a);
            } else {
                b = c;
                code_b = getCode(rectangle, b);
            }
        }
        s._point1 = a;
        s._point2 = b;
        /* ��� ���� ����� 0, ������������� ��� ����� � �������������� */
        return false;
    }

    int getCode (Rectangle rectangle, Point point) {
        int position = 0;
        if (point.x < rectangle.BottomLeft.x)
            position += Position.LEFT.code();
        if (point.x > rectangle.TopRight.x)
            position += Position.RIGHT.code();
        if (point.y < rectangle.BottomLeft.y)
            position += Position.BOT.code();
        if (point.y > rectangle.TopRight.y)
            position += Position.TOP.code();
        return position;
    }
}

class Rectangle {
    protected Point BottomLeft, TopRight;

    Rectangle() {
        BottomLeft = new Point();
        TopRight = new Point();
    }

    Rectangle(Point BL, Point TR) {
        BottomLeft = BL;
        TopRight = TR;
    }
}

class Segment {
    protected Point point1, point2;
    protected Point _point1, _point2;
    int i;

    Segment() {
        point1 = new Point();
        point2 = new Point();
    }

    Segment(Point p1, Point p2) {
        point1 = p1;
        point2 = p2;
        _point1 = new Point(p1);
        _point2 = new Point(p2);
    }
}