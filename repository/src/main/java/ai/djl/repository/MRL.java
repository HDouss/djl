/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.repository;

import java.net.URI;

/**
 * A class {@code URL} represents a Machine learning Resource Locator, a pointer to a "resource" on
 * a machine learning {@link Repository}.
 */
public class MRL {

    private Anchor baseAnchor;
    private String groupId;
    private String artifactId;

    public MRL(Anchor baseAnchor, String groupId, String artifactId) {
        this.baseAnchor = baseAnchor;
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    public URI toURI() {
        String groupIdPath = groupId.replace('.', '/');
        Anchor anchor = baseAnchor.resolve(groupIdPath, artifactId);
        return URI.create(anchor.getPath() + '/');
    }

    public Anchor getBaseAnchor() {
        return baseAnchor;
    }

    public void setBaseAnchor(Anchor baseAnchor) {
        this.baseAnchor = baseAnchor;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return toURI().toString();
    }

    public interface Dataset {

        Anchor CV = new Anchor("dataset/cv");
        Anchor NLP = new Anchor("dataset/nlp");
    }

    public interface Model {

        interface CV {

            Anchor IMAGE_CLASSIFICATION = new Anchor("model/cv/image_classification");
            Anchor OBJECT_DETECTION = new Anchor("model/cv/object_detection");
            Anchor SEMANTIC_SEGMENTATION = new Anchor("model/cv/semantic_segmentation");
            Anchor INSTANCE_SEGMENTATION = new Anchor("model/cv/instance_segmentation");
            Anchor POSE_ESTIMATION = new Anchor("model/cv/pose_estimation");
            Anchor ACTION_RECOGNITION = new Anchor("model/cv/action_recognition");
        }

        interface NLP {

            Anchor QUESTION_ANSWER = new Anchor("model/nlp/qa");
        }
    }
}
