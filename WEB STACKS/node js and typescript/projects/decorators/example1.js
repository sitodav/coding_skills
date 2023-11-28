"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
//METHOD decorator FACTORY
function MyLoggingDecorator(_originalMethod, _context) {
    return function (target, propertyKey, descriptor) {
        const original = descriptor.value;
        descriptor.value = function (...args) {
            console.log('Logged inputs:', args);
            const result = original.apply(this, args);
            console.log('Logged outputs', result);
            return result;
        };
    };
}
class Student {
    constructor(name, surname) {
        this.name = name;
        this.surname = surname;
        this.printMe = () => {
            console.log("my name is " + this.name + " and surname is " + this.surname);
        };
        this.returnDescription = () => {
            return this.name + ", " + this.surname;
        };
    }
}
__decorate([
    MyLoggingDecorator
], Student.prototype, "printMe", void 0);
let student = new Student("Davide", "Sito");
student.printMe();
