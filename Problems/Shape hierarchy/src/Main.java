abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    double a;
    double b;
    double c;

    Triangle(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    double getPerimeter(){
        return a+b+c;
    }

    double getArea(){
        double halfP = this.getPerimeter()/2;
        return Math.sqrt(halfP*(halfP - a)*(halfP - b)*(halfP - c));
    }

}

class Rectangle extends Shape {
    double a;
    double b;

    Rectangle(double a, double b){
        this.a = a;
        this.b = b;
    }

    double getPerimeter(){
        return 2*(a+b);
    }

    double getArea(){

        return a*b;
    }

}

class Circle extends Shape {
    double r;

    Circle(double r){
        this.r = r;
    }

    double getPerimeter(){
        return 2*r*Math.PI;
    }

    double getArea(){

        return Math.PI*r*r;
    }

}
