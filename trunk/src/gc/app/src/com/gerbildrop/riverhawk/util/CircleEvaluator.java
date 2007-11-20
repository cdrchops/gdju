package com.gerbildrop.riverhawk.util;

import com.gerbildrop.riverhawk.base.Configuration;

import javax.media.opengl.GL;

public class CircleEvaluator {
/*=========================================================================

  OpenGC - The Open Source Glass Cockpit Project
  Please see our web site at http://www.opengc.org

  Module:  $RCSfile: ogcCircleEvaluator.cpp,v $

  Last modification:
    Date:      $Date: 2004/10/14 19:27:52 $
    Version:   $Revision: 1.1.1.1 $
    Author:    $Author: damion $

  Copyright (c) 2001-2003 Damion Shelton
  All rights reserved.
  See Copyright.txt or http://www.opengc.org/Copyright.htm for details.

  This software is distributed WITHOUT ANY WARRANTY; without even
  the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
  PURPOSE.  See the above copyright notice for more information.

=========================================================================*/
    /**
     * The origin (center) of the circle
     */
    private double m_XOrigin, m_YOrigin;

    /**
     * The radius of the arc/circle
     */
    private double m_Radius;

    /**
     * The start and end of the arc in degrees
     */
    private double m_StartArcDegrees, m_EndArcDegrees;

    /**
     * How many degrees to move before generating a new point
     */
    private double m_DegreesPerPoint;

    public CircleEvaluator() {
        m_XOrigin = 0;
        m_YOrigin = 0;

        m_StartArcDegrees = 0;
        m_EndArcDegrees = 0;

        m_DegreesPerPoint = 10;

        m_Radius = 1;
    }

    public void setOrigin(double x, double y) {
        m_XOrigin = x;
        m_YOrigin = y;
    }

    public void setArcStartEnd(double startArc, double endArc) {
        m_StartArcDegrees = startArc;
        m_EndArcDegrees = endArc;
    }

    public void setDegreesPerPoint(double degreesPerPoint) {
        m_DegreesPerPoint = degreesPerPoint;
    }

    public void evaluate() {
        // Add the vertexes specified
        double x;
        double y;

        double pi = 3.14159265;

        // We parametrically evaluate the circle
        // x = sin(t)
        // y = cos(t)
        // t goes from 0 to 2pi
        // 0 degrees = 0rad, 90 degrees = pi/2rad, etc.

        double startRad = m_StartArcDegrees / 180 * pi;

        double endRad = m_EndArcDegrees / 180 * pi;

        double radPerPoint = m_DegreesPerPoint / 180 * pi;

        if (startRad > endRad)
            endRad += 2 * pi;

        double currentRad = startRad;

        GL gl = Configuration.getGL();

        do {
            x = m_Radius * Math.sin(currentRad) + m_XOrigin;
            y = m_Radius * Math.cos(currentRad) + m_YOrigin;

            gl.glVertex2f((float) x, (float) y);

            currentRad += radPerPoint;

        } while (currentRad < endRad);

        x = m_Radius * Math.sin(endRad) + m_XOrigin;
        y = m_Radius * Math.cos(endRad) + m_YOrigin;

        gl.glVertex2f((float) x, (float) y);
    }

    public void setRadius(double radius) {
        if (radius > 0)
            m_Radius = radius;
        else
            m_Radius = 1;
    }
}