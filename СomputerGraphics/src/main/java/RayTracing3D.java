import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class RayTracing3D {
    private Vector3d camera = new Vector3d(0,40,0);
    private int dirZ = 1;
    private double fov = Math.PI/2;
    public  Vector<Sphere3D> AllSpheres;
    public  Vector<Light3D> AllLights;
    public  Vector<TriangleModel> AllTriangleModels;
    public  Sphere3D OneSphere;
    public  Light3D OneLight;
    public  TriangleModel OneTriangleModel;
    private  Vector3d BackgroundColor = new Vector3d(0.3,0.5,0.4);
    //private  Vector3d BackgroundColor = new Vector3d(-1,-1,-1);
    private  int maxDepth=4;
    public int width;
    public int height;
    public Display3D Display;
    private int threadsAmount = 8;

    public RayTracing3D(JTable TableSpheres, JTable TableLights, Display3D _display){
        Display = _display;
        width =  Display.widthImage;
        height = Display.heightImage;
        AllSpheres = new Vector<Sphere3D>();
        AllLights = new Vector<Light3D>();
        AllTriangleModels = new Vector<TriangleModel>();

        for(int i=0; i<TableSpheres.getRowCount(); i++) {
            OneSphere = new Sphere3D(TableSpheres, i);
            AllSpheres.add(OneSphere);
        }

        for(int i=0; i<TableLights.getRowCount(); i++) {
            OneLight = new Light3D(TableLights, i);
            AllLights.add(OneLight);
        }

        OneTriangleModel = new TriangleModel( "src/main/resources/3d/duck.obj",
                MaterialType.REDWOOD);
        double x = -5.10949;
        double y = 4.13437;
        double z = 9.007105;
        OneTriangleModel.MoveModel(0+x, 20+y,75+z);
        AllTriangleModels.add(OneTriangleModel);
        //���
        OneTriangleModel = new TriangleModel(new Vector3d(0, -20, -35),
                new Vector3d(-105, -20, 70),
                new Vector3d(0,-20,175),
                new Vector3d(105, -20, 70),
                MaterialType.STEEL);
        AllTriangleModels.add(OneTriangleModel);

        //�������
        OneTriangleModel = new TriangleModel(new Vector3d(0, 80, -35),
                new Vector3d(105, 80,70),
                new Vector3d(0,80,175),
                new Vector3d(-105,80 ,70),
                MaterialType.STEEL);
        AllTriangleModels.add(OneTriangleModel);

        //����� ������� �����
        OneTriangleModel = new TriangleModel(new Vector3d(0,-20,-35),
                new Vector3d(0,80,-35),
                new Vector3d(-105,80,70),
                new Vector3d(-105,-20,70),
                MaterialType.STEEL);
        AllTriangleModels.add(OneTriangleModel);

        //������ ������� �����
        OneTriangleModel = new TriangleModel(new Vector3d(0,-20,-35),
                new Vector3d(105, -20 , 70),
                new Vector3d(105, 80, 70),
                new Vector3d(0, 80, -35),
                MaterialType.STEEL);
        AllTriangleModels.add(OneTriangleModel);

        //������ ������� �����
        OneTriangleModel = new TriangleModel(new Vector3d(0,-20,175),
                new Vector3d(0,80,175),
                new Vector3d(105,80,70),
                new Vector3d(105, -20, 70),
                MaterialType.STEEL);
        AllTriangleModels.add(OneTriangleModel);
        //����� ������� �����(1)
        OneTriangleModel = new TriangleModel(new Vector3d(0,-20,175),
                new Vector3d(-105,-20,70),
                new Vector3d(-105, 20, 70),
                new Vector3d(0, 20, 175),
                MaterialType.STEEL);
        AllTriangleModels.add(OneTriangleModel);
        //����� ������� �����(2)
        OneTriangleModel = new TriangleModel(new Vector3d(0,40,175),
                new Vector3d(-105,40,70),
                new Vector3d(-105, 80, 70),
                new Vector3d(0, 80, 175),
                MaterialType.STEEL);
        AllTriangleModels.add(OneTriangleModel);

        //�������� ����� �����
        OneTriangleModel = new TriangleModel(new Vector3d(-15, -20 ,60),
                new Vector3d(-15, 20,60),
                new Vector3d(15, 20, 60),
                new Vector3d(15, -20,60),
                MaterialType.WOOD);
        AllTriangleModels.add(OneTriangleModel);
        //������ ����� �����
        OneTriangleModel = new TriangleModel(new Vector3d(15,-20,60),
                new Vector3d(15,20, 60),
                new Vector3d(15,20,90),
                new Vector3d(15, -20, 90),
                MaterialType.WOOD);
        AllTriangleModels.add(OneTriangleModel);
        //������ ����� �����
        OneTriangleModel = new TriangleModel(new Vector3d(-15, -20, 90),
                new Vector3d(15, -20,90),
                new Vector3d(15,20,90),
                new Vector3d(-15,20,90),
                MaterialType.WOOD);
        AllTriangleModels.add(OneTriangleModel);
        //����� ����� �����
        OneTriangleModel = new TriangleModel(new Vector3d(-15, -20 ,90),
                new Vector3d(-15,20,90),
                new Vector3d(-15,20,60),
                new Vector3d(-15, -20, 60),
                MaterialType.WOOD);
        AllTriangleModels.add(OneTriangleModel);
        //������ �����
        OneTriangleModel = new TriangleModel(new Vector3d(-15,20,60),
                new Vector3d(-15,20,90),
                new Vector3d(15,20,90),
                new Vector3d(15, 20,60),
                MaterialType.WOOD);
        AllTriangleModels.add(OneTriangleModel);
        /*
        //1� �������
        OneTriangleModel = new TriangleModel(new Vector3d(10.25, 40, 75), new Vector3d(10.25, 60 ,75), new Vector3d(12, 60, 67.25), MaterialType.MIRROR);
        AllTriangleModels.add(OneTriangleModel);
        //2� �������
        OneTriangleModel = new TriangleModel(new Vector3d(4, 40, 80), new Vector3d(4, 60, 80), new Vector3d(10.25, 60, 75), MaterialType.MIRROR);
        AllTriangleModels.add(OneTriangleModel);
        //3� �������
        OneTriangleModel = new TriangleModel(new Vector3d(4,40,80), new Vector3d(-4, 40, 80), new Vector3d(-4 , 60, 80), MaterialType.MIRROR);
        AllTriangleModels.add(OneTriangleModel);
        //4 �������
        OneTriangleModel = new TriangleModel(new Vector3d(-4, 60,80), new Vector3d(-4,60,80), new Vector3d(-10.25, 60,75), MaterialType.MIRROR);
        AllTriangleModels.add(OneTriangleModel);
        //5� �������
        OneTriangleModel = new TriangleModel(new Vector3d(-10.25, 40, 75), new Vector3d(-10.25, 60,75), new Vector3d(-12,60,67.25), MaterialType.MIRROR);
        AllTriangleModels.add(OneTriangleModel);
        */
    }

    public void RenderScene(Display3D display) {
        for (int j = 0; j < height; ++j) {//�� y
            for (int i = 0; i < width; ++i) {//�� �
                RenderOnePixel(i, j);
            }
        }
    }




    public void RenderOnePixel( int i, int j){
        double x = (2 * (i + 0.5) / width - 1) * Math.tan(fov / 2) * width / height;
        double y = -(2 * (j + 0.5) / height - 1) * Math.tan(fov / 2);
        Vector3d dir = (new Vector3d(x , y , dirZ )).normalize();

        Vector3d color_kef = CalculateSpherePixelColor(camera, dir, 0);

        double max_color_kef = Math.max(color_kef.x, Math.max(color_kef.y, color_kef.z));
        if(max_color_kef>1)
            color_kef = color_kef.getVectorScaled(1./max_color_kef);
        Color pixelColor = new Color((int)(255*Math.max(0, Math.min(1,color_kef.x))),
                (int)(255*Math.max(0, Math.min(1,color_kef.y))),
                (int)(255*Math.max(0, Math.min(1,color_kef.z))));
        if(pixelColor!=null)
            Display.AddPointOnDisplayRT(i,height-j,pixelColor);
    }


    public  Vector3d CalculateSpherePixelColor(Vector3d orig, Vector3d dir, int depth){
        SceneIntersect scene = new SceneIntersect(orig,dir,AllSpheres,AllTriangleModels);

        if (depth<=maxDepth && scene.isIntersect) {
            Vector3d reflect_dir = reflect(dir, scene.N);
            Vector3d refract_dir = refract(dir, scene.N, scene.material.refractive).normalize();
            Vector3d reflect_orig = reflect_dir.getScalar(scene.N) < 0 ?
                    scene.hit.getSubtraction(scene.N.getVectorScaled(0.001)) :
                    scene.hit.getAddition(scene.N.getVectorScaled(0.001));
            Vector3d refract_orig = refract_dir.getScalar(scene.N) < 0 ?
                    scene.hit.getSubtraction(scene.N.getVectorScaled(0.001)) :
                    scene.hit.getAddition(scene.N.getVectorScaled(0.001));
            Vector3d reflect_color = CalculateSpherePixelColor(
                    reflect_orig,
                    reflect_dir,
                    depth+1);
            Vector3d refract_color = CalculateSpherePixelColor(
                    refract_orig,
                    refract_dir,
                    depth+1);

            double diffuse_light_intensity = 0,
                    specular_light_intensity = 0;

            for (int i=0; i<AllLights.size(); i++) {
                Vector3d light_dir = (AllLights.elementAt(i).position.getSubtraction(scene.hit)).normalize();
                double light_dist = (AllLights.elementAt(i).position.getSubtraction(scene.hit)).getModule();

                Vector3d shadow_orig = (light_dir.getScalar(scene.N) < 0) ?
                        scene.hit.getSubtraction(scene.N.getVectorScaled(0.001)) :
                        scene.hit.getAddition(scene.N.getVectorScaled(0.001)); // checking if the point lies in the shadow of the lights[i]

                SceneIntersect shadow = new SceneIntersect(
                        shadow_orig,
                        light_dir,
                        AllSpheres,
                        AllTriangleModels);
                if (shadow.isIntersect){
                    double shadow_l = (shadow.hit.getSubtraction(shadow_orig)).getModule();
                    if(shadow_l<light_dist)
                        continue;
                }

                diffuse_light_intensity += AllLights.elementAt(i).intensity * Math.max(0, light_dir.getScalar(scene.N));
                specular_light_intensity += Math.pow(
                        Math.max(0, reflect(light_dir,scene.N).getScalar(dir)),
                        scene.material.specularExponent)
                        *AllLights.elementAt(i).intensity;

            }

            Vector3d result_0 = scene.material.color.getVectorScaled(diffuse_light_intensity * scene.material.albedo[0]);
            Vector3d result_1 = (new Vector3d(1.,1.,1.)).getVectorScaled(specular_light_intensity * scene.material.albedo[1]);
            Vector3d result_2 = reflect_color.getVectorScaled(scene.material.albedo[2]);
            Vector3d result_3 = refract_color.getVectorScaled(scene.material.albedo[3]);
            return  result_0.getAddition(result_1.getAddition(result_2.getAddition(result_3)));// sphere color//resultColor
        }
        return BackgroundColor;
    }


    public  Vector3d reflect(Vector3d i, Vector3d n) {
        double I_N=i.getScalar(n);
        return i.getSubtraction(n.getVectorScaled(2.f*I_N));
    }

    public  Vector3d refract(Vector3d i, Vector3d n, double etaT) {
        double etaI = 1, buf;
        double cosi = -Math.max(-1, Math.min(1,i.getScalar(n)));
        Vector3d n_new = n;
        if(cosi<0){
            cosi = -cosi;
            buf=etaT; etaT=etaI; etaI=buf;
            n_new = n.getVectorScaled(-1);
        }

        double eta = etaI/etaT;
        double k = 1 - eta*eta*(1 - cosi*cosi);
        return k<0 ?
                new Vector3d(0,0,0) :
                (i.getVectorScaled(eta)).getAddition(n_new.getVectorScaled(eta*cosi - Math.sqrt(k)));
    }

}
