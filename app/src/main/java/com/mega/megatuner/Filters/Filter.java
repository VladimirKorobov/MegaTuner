package com.mega.megatuner.Filters;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir-Desktop
 * Date: 16.10.13
 * Time: 23:52
 * To change this template use File | Settings | File Templates.
 */
public class Filter{
    public static class Vector{
        public double[] m_v = null;

        public Vector(int n) {
            m_v = new double[n];
        }

        public Vector(int n, int num, double koef) {
            m_v = new double[n];
            m_v[num] = koef;
        }
    }

    public static class Vector3 extends Vector{

        public Vector3() {
            super(3);
        }

        public Vector3(int num, double koef) {
            super(3, num, koef);
        }

        public static Vector3 Plus(Vector3 Vector1, Vector3 Vector2) {
            Vector3 v = new Vector3();
            for (int i = 0; i < v.m_v.length; i++)
                v.m_v[i] = Vector1.m_v[i] + Vector2.m_v[i];
            return v;
        }

        public static Vector3 Mult(Vector3 Vector, double coef) {
            Vector3 v = new Vector3();
            for (int i = 0; i < v.m_v.length; i++)
                v.m_v[i] = Vector.m_v[i] * coef;
            return v;
        }
    }

    public static class Vector5 extends Vector{

        public Vector5() {
            super(5);
        }

        public Vector5(int num, double koef) {
            super(5, num, koef);
        }

        public static Vector5 Plus(Vector5 Vector1, Vector5 Vector2) {
            Vector5 v = new Vector5();
            for (int i = 0; i < v.m_v.length; i++)
                v.m_v[i] = Vector1.m_v[i] + Vector2.m_v[i];
            return v;
        }

        public static Vector5 Mult(Vector5 Vector, double coef) {
            Vector5 v = new Vector5();
            for (int i = 0; i < v.m_v.length; i++)
                v.m_v[i] = Vector.m_v[i] * coef;
            return v;
        }
    }
/*
    public static class RK4{
        protected double m_k0, m_k1, m_k2;
        protected double m_m0, m_m1, m_m2;
        protected double m_Uder, m_Uout, m_Uout1;

        public void Init(double freq, double h, double q) {

        }

        public void InitWithoutOperators(double kIn, double kDer, double kOut, double h) {
            Vector3 Uin = new Vector3(0, 1.0);
            Vector3 Uout = new Vector3(1, 1.0);
            Vector3 Uder = new Vector3(2, 1.0);

            Vector3 K1;
            Vector3 K2;
            Vector3 K3;
            Vector3 K4;

            Vector3 M1;
            Vector3 M2;
            Vector3 M3;
            Vector3 M4;

            Vector3 k1, k2, k3;
            k1 = Vector3.Mult(Uin, kIn);
            k2 = Vector3.Mult(Uout, kOut);
            k3 = Vector3.Mult(Uder, kDer);

            K1 = Vector3.Plus(Vector3.Plus(k1, k2), k3);
            M1 = Uder;

            k1 = Vector3.Mult(Uin, kIn);
            k2 = Vector3.Mult(Vector3.Plus(Uout, Vector3.Mult(M1, h / 2)), kOut);
            k3 = Vector3.Mult(Vector3.Plus(Uder, Vector3.Mult(K1, h / 2)), kDer);

            K2 = Vector3.Plus(Vector3.Plus(k1, k2), k3);
            M2 = Vector3.Plus(Uder, Vector3.Mult(K1, h / 2));

            k1 = Vector3.Mult(Uin, kIn);
            k2 = Vector3.Mult(Vector3.Plus(Uout, Vector3.Mult(M2, h / 2)), kOut);
            k3 = Vector3.Mult(Vector3.Plus(Uder, Vector3.Mult(K2, h / 2)), kDer);

            K3 = Vector3.Plus(Vector3.Plus(k1, k2), k3);
            M3 = Vector3.Plus(Uder, Vector3.Mult(K2, h / 2));

            k1 = Vector3.Mult(Uin, kIn);
            k2 = Vector3.Mult(Vector3.Plus(Uout, Vector3.Mult(M3, h)), kOut);
            k3 = Vector3.Mult(Vector3.Plus(Uder, Vector3.Mult(K3, h)), kDer);

            K4 = Vector3.Plus(Vector3.Plus(k1, k2), k3);
            M4 = Vector3.Plus(Uder, Vector3.Mult(K3, h));

            k1 = Vector3.Plus(M1, Vector3.Mult(M2, 2));
            k1 = Vector3.Plus(k1, Vector3.Mult(M3, 2));
            k1 = Vector3.Plus(k1, M4);
            k1 = Vector3.Mult(k1, (h / 6));
            Vector3 Uout1 = Vector3.Plus(Uout, k1);

            k1 = Vector3.Plus(K1, Vector3.Mult(K2, 2));
            k1 = Vector3.Plus(k1, Vector3.Mult(K3, 2));
            k1 = Vector3.Plus(k1, K4);
            k1 = Vector3.Mult(k1, (h / 6));
            Vector3 Uder1 = Vector3.Plus(Uder, k1);

            m_k0 = Uder1.m_v[0];
            m_k1 = Uder1.m_v[1];
            m_k2 = Uder1.m_v[2];

            m_m0 = Uout1.m_v[0];
            m_m1 = Uout1.m_v[1];
            m_m2 = Uout1.m_v[2];
        }

        public double Next(short[] input, int index) {
            return 0;
        }

    }

    public static class RK4FilterParallel extends RK4{
        double m_freq;
        double m_q;
        double m_h_1;
        double m_Uout_1;

        public void Init(double freq, double h, double q) {
            double w = 2 * Math.PI * freq;
            m_freq = freq;
            m_q = q;
            h *= 2;

            double kIn = q; //
            double kOut = -1;
            double kDer = -q;
            m_h_1 = 1.0 / (h * w);
            //Init(kIn, kDer, kOut, m_h);
            InitWithoutOperators(kIn, kDer, kOut, h * w);
        }


        public RK4FilterParallel(double freq, double h, double q) {
            Init(freq, h, q);
        }

        public double Next(short[] input, int index) {
            double Uin = input[index];

            double res = m_m0 * Uin + m_m1 * m_Uout + m_m2 * m_Uder;
            m_Uder = m_k0 * Uin + m_k1 * m_Uout + m_k2 * m_Uder;


            double outRes = (res - m_Uout1) / 2 * m_h_1;
            m_Uout_1 = m_Uout;
            m_Uout = res;
            return outRes;
        }
    }
*/
    public static class RK4{
        protected double m_k0, m_k1, m_k2, m_k3, m_k4;
        protected double m_m0, m_m1, m_m2, m_m3, m_m4;
        protected double m_Uder, m_Uout;
        protected double m_I2 = 0, m_I1 = 0;

        public RK4(double freq, double h, double q) {
            InitWithoutOperators(freq, h, q);
        }

        public void Init(double f, double h, double q) {
            InitWithoutOperators(f, h, q);
        }

        private void InitWithoutOperators(double f, double h, double q) {
            Vector5 a1, a2, a3;
            double w = 2 * Math.PI * f;

            double k1 = w * q;
            double k2 = -k1;
            double k3 = -w;
            double m2 = w;


            Vector5 I_0 = new Vector5(0, 1.0);
            Vector5 I_1 = new Vector5(1, 1.0);
            Vector5 I_2 = new Vector5(2, 1.0);
            Vector5 U_der = new Vector5(3, 1.0);
            Vector5 U_out = new Vector5(4, 1.0);

            Vector5 K1;
            Vector5 K2;
            Vector5 K3;
            Vector5 K4;

            Vector5 M1;
            Vector5 M2;
            Vector5 M3;
            Vector5 M4;

            a1 = Vector5.Mult(I_2, k1);
            a2 = Vector5.Mult(U_der, k2);
            a3 = Vector5.Mult(U_out, k3);

            M1 = Vector5.Mult(Vector5.Plus(a1, Vector5.Plus(a2, a3)), h);
            K1 = Vector5.Mult(U_der, m2 * h);

            a1 = Vector5.Mult(I_1, k1);
            a2 = Vector5.Mult(Vector5.Plus(U_der, Vector5.Mult(M1, 0.5)), k2);
            a3 = Vector5.Mult(Vector5.Plus(U_out, Vector5.Mult(K1, 0.5)), k3);

            M2 = Vector5.Mult(Vector5.Plus(a1, Vector5.Plus(a2, a3)), h);
            K2 = Vector5.Mult(Vector5.Plus(U_der, Vector5.Mult(M1, 0.5)), m2 * h);

            a1 = Vector5.Mult(I_1, k1);
            a2 = Vector5.Mult(Vector5.Plus(U_der, Vector5.Mult(M2, 0.5)), k2);
            a3 = Vector5.Mult(Vector5.Plus(U_out, Vector5.Mult(K2, 0.5)), k3);

            M3 = Vector5.Mult(Vector5.Plus(a1, Vector5.Plus(a2, a3)), h);
            K3 = Vector5.Mult(Vector5.Plus(U_der, Vector5.Mult(M2, 0.5)), m2 * h);

            a1 = Vector5.Mult(I_0, k1);
            a2 = Vector5.Mult(Vector5.Plus(U_der, M3), k2);
            a3 = Vector5.Mult(Vector5.Plus(U_out, K3), k3);


            M4 = Vector5.Mult(Vector5.Plus(a1, Vector5.Plus(a2, a3)), h);
            K4 = Vector5.Mult(Vector5.Plus(U_der, M3), m2 * h);


            a1 = Vector5.Plus(M1, Vector5.Mult(M2, 2));
            a1 = Vector5.Plus(a1, Vector5.Mult(M3, 2));
            a1 = Vector5.Plus(a1, M4);
            a1 = Vector5.Mult(a1, (1.0 / 6));
            Vector5 Uder = Vector5.Plus(U_der, a1);

            a1 = Vector5.Plus(K1, Vector5.Mult(K2, 2));
            a1 = Vector5.Plus(a1, Vector5.Mult(K3, 2));
            a1 = Vector5.Plus(a1, K4);
            a1 = Vector5.Mult(a1, (1.0 / 6));
            Vector5 Uout = Vector5.Plus(U_out, a1);

            m_m0 = Uder.m_v[0];
            m_m1 = Uder.m_v[1];
            m_m2 = Uder.m_v[2];
            m_m3 = Uder.m_v[3];
            m_m4 = Uder.m_v[4];

            m_k0 = Uout.m_v[0];
            m_k1 = Uout.m_v[1];
            m_k2 = Uout.m_v[2];
            m_k3 = Uout.m_v[3];
            m_k4 = Uout.m_v[4];
        }


        public double Next(short[] input, int index) {
            double Iin = input[index];

            double Uder = m_m0 * Iin + m_m1 * m_I1 + m_m2 * m_I2 + m_m3 * m_Uder + m_m4 * m_Uout;
            double Uout = m_k0 * Iin + m_k1 * m_I1 + m_k2 * m_I2 + m_k3 * m_Uder + m_k4 * m_Uout;

            m_I2 = m_I1;
            m_I1 = Iin;

            m_Uder = Uder;
            m_Uout = Uout;

            return m_Uder;
        }
    }
}