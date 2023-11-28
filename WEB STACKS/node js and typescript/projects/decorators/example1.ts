
//METHOD decorator FACTORY
function MyLoggingDecorator(_originalMethod: any, _context: any) {
    return function (target: any, propertyKey: string, descriptor: PropertyDescriptor) {
        const original = descriptor.value;

        descriptor.value = function (...args: any[]) {
            console.log('Logged inputs:', args);
            const result = original.apply(this, args);
            console.log('Logged outputs', result);

            return result;
        };
    };

}

class Student {
    constructor(public name, public surname) { }

    @MyLoggingDecorator
    public printMe = (): void => {
        console.log("my name is " + this.name + " and surname is " + this.surname);
    }


    public returnDescription = (): string => {
        return this.name + ", " + this.surname;
    }
}



let student: Student = new Student("Davide", "Sito");
student.printMe();