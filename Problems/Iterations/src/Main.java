static void performIterationsWithCallback(int numberOfIterations, LoopCallback callback) {
        for (int i = 0; i < numberOfIterations; i++) {
        callback.onNewIteration(i);
        }
        }

static void startIterations(int numberOfIterations) {
        LoopCallback lcb = new LoopCallback(){

@Override
public void onNewIteration(int iter){
        System.out.println("Iteration: "+iter);
        }
        };
        performIterationsWithCallback(numberOfIterations, lcb);

        }
