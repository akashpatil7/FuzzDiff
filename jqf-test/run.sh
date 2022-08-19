#!/bin/bash
echo "Original class name: "
read original
echo ""

echo "Refactored class name: "
read refactored
echo ""

echo "Method name: "
read method_name
echo ""

echo "Fuzzing....."
OP=$(mvn exec:java -Dexec.args="$original $refactored $method_name")
echo ""

#echo "$OP"
sleep 5

SUB="ASSERTION FAILURES"
MNF="METHOD_NOT_FOUND"
CNF="CLASS_NOT_FOUND"
FAIL="BUILD FAILURE"

if [[ "$OP" == *"$CNF"* ]]; then
  echo "------------------------------------------------------------"
  echo "Error: One or both class(es) not found in classes/ directory"
  echo "------------------------------------------------------------"
  sleep 10
  exit
fi

if [[ "$OP" == *"$MNF"* ]]; then
  echo "-----------------------------------------------------------"
  echo "Error: Method $method_name not found in one or both classes"
  echo "-----------------------------------------------------------"
  sleep 10
  exit
fi

if [[ "$OP" == *"$SUB"* ]]; then
  echo "--------------------------------------------"
  echo "RESULT: The two programs are not equivalent."
  echo "--------------------------------------------"
  echo "There were failures in fuzz testing.
            Reproducing failures and generating coverage file......"
  mvn jqf:repro -Doriginal=$original -Drefactored=$refactored
                -DmethodName=$method_name
                -Dclass=ProgramEquivalenceTest
                -Dmethod=fuzzTestForEquivalence
                -Dinput=target/fuzz-results/ProgramEquivalenceTest
                        /fuzzTestForEquivalence/failures
                -DlogCoverage=coverage.txt -DprintArgs
                -DdumpArgs=result/
  sleep 20
  exit
else
  echo "There were no failures in fuzz testing.
            Reproducing results and generating coverage file......"
  COV=$(mvn jqf:repro -Doriginal=$original -Drefactored=$refactored
                -DmethodName=$method_name
                -Dclass=ProgramEquivalenceTest
                -Dmethod=fuzzTestForEquivalence
                -Dinput=target/fuzz-results/ProgramEquivalenceTest
                        /fuzzTestForEquivalence/corpus
                -DlogCoverage=coverage.txt -DprintArgs
                -DdumpArgs=result/)
fi
echo ""

sleep 5
echo "Executing semantic tests................"
echo ""

RESULT=$(mvn test -Dtest="AdditionalChecksTest")

SUC="SUCCESS"

echo "--------------------------------------------"
if [[ "$RESULT" == *"$SUC"* ]]; then
  echo "RESULT: The two programs are equivalent."
  echo "--------------------------------------------"
else
  echo "RESULT: The two programs are not equivalent."
  echo "--------------------------------------------"
  echo "$RESULT"
fi

sleep 120