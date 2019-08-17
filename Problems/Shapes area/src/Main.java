import java.lang.Math;

class Shape {

    public double area() {
        return 0;
    }
}

class Triangle extends Shape {
    double height;
    double base;

    @Override
    public double area() {

        return 0.5*height*base;
    }
}

class Circle extends Shape {
    double radius;

    @Override
    public double area() {
        return Math.PI*radius*radius;
    }
}

class Square extends Shape {
    double side;

    @Override
    public double area() {
        return side*side;
    }
}

class Rectangle extends Shape {
    double width;
    double height;

    @Override
    public double area() {
        return width*height;
    }
}