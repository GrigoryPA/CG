import java.awt.*;
import java.util.Vector;

/*
    1. ������� ������� ��������������� � ����
        1.1. ������� ������� ���������������
            1.1.1. ��������� ������������ A, B, C, D ��������� ��������� � Color �����.
        1.2. ������� ������� ����
            1.2.1. ������� � �� ��� ���������������� ���� Y_min != Y_max.
                   Y_min, X_min, Y_max, X_max, tg_alpha (= delta_Y / delta_X).
    2.
        2.1. ���������� ���� �� ����������� Y_min � ����������� � ������� ��������� ����������.
             ��� ������� �������� ������� �����, ������� ����� ����� �������. ����� ����� ������ ������.
        2.2. ���� Y_min �����, ������������� ������������� �� �������� tg_alpha.
    3.
        3.1. ������������ ������� �������� ����, ��� ������� Y_tek = Y_min_i
    4. ��������� ���
        4.1.
            4.1.1. ���������� ���������� �������� �����. ����������� ���������������� ����� i ����� �������������� � ��,
                   � ��� ����.
            4.1.2. ����������� ���� ����� ��������������.
            4.1.3. � ������������ � ����� ������� ������������ ���� ������������ ���� � ��� �������������� ���������������
             ������ ��� �������� x_min_i.
            4.1.4. ���������� �������� ������ N �������� ��������������� ��������������� ������ 1, N = 1.
        4.2.
            4.2.1. ������������� ���������� ��������, ������� �����.
*/
public class ScanLine3D {

    void ScanLine(Vector<Polyhedron> PolyhedronTable, Vector<Edge> edgeTable) {

    }

}

class Polyhedron {
    double A, B, C, D;
    Color color;

    public Polyhedron(double a, double b, double c, double d, Color color) {
        A = a;
        B = b;
        C = c;
        D = d;
        this.color = color;
    }
}

class Edge {
    double x_min, y_min;
    double x_max, y_max;
    double tg_alpha;

    public Edge(double x_min, double y_min, double x_max, double y_max) {
        this.x_min = x_min;
        this.y_min = y_min;
        this.x_max = x_max;
        this.y_max = y_max;
        this.tg_alpha = (y_max - y_min) / (x_max - x_min);
    }
}
