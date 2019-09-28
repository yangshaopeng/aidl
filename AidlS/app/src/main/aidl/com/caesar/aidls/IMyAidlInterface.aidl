// IMyAidlInterface.aidl
package com.caesar.aidls;
import com.caesar.aidls.Person;
// Declare any non-default types here with import statements

interface IMyAidlInterface {

    int add(int num1, int num2);

    // 如果传递类似于集合大数据元素，需要制定是输入、输出或者输入+输入。 in out inout
    // 多进程在通信期间，底层传递的是基本元素，A进程传递给B进程期间，会将A进程集合数据进行拆分，B进程又会进行组装，两步都会消耗内存。
    // 如果指定为输入参数，
    // 为什么要指定输入输出？？？
    List<Person> addPerson(in Person person);

}
