#!/bin/bash

# set default values for parameters
PARAMETER_P=true
PARAMETER_D=true

# read command line options
while getopts "PD" opt; do
  case $opt in
    P)
      PARAMETER_P=false
      ;;
    D)
      PARAMETER_D=false
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      exit 1
      ;;
  esac
done


# output parameter values
echo "Build Package ?: $PARAMETER_P"
echo "Build Dependencies ?: $PARAMETER_D"

if [ "$PARAMETER_P" = true ] ; then
  echo "*** Compiling and packaging the application ***"
  echo "*** Application packaged successfully ***"
fi

if [ "$PARAMETER_D" = true ] ; then
  echo "*** Building dependencies ***"
  echo "*** Dependencies built successfully ***"
fi