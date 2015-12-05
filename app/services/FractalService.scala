package services

import java.util

import org.bsheehan.fractal.{ColorInfo, ColorSet, FractalFactory, FractalInfo}

/**
 * Created by bob on 11/28/15.
 */
object FractalService {

  def getFractals : util.List[FractalInfo] = {
     FractalFactory.getFractals
  }

  def getColors : util.List[ColorInfo] = {
    ColorSet.getColorInfos
  }
}
