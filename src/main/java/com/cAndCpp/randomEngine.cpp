#include "com_java_tools_GameTool.h"
#include <random>
#include "jni.h"
#include <stdlib.h>

JNIEXPORT jboolean JNICALL Java_com_java_tools_GameTool_randomReturnBoolean(JNIEnv *env, jclass jc, jint seed, jdouble trueProbability)
{
    std::default_random_engine e(seed);
    std::bernoulli_distribution randomBoolean(trueProbability);

    return randomBoolean(e);
}

JNIEXPORT void JNICALL Java_com_java_tools_GameTool_cls(JNIEnv *env, jclass cl)
{
    system("cls");
}
